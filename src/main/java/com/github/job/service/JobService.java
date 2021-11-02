package com.github.job.service;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.executor.handler.JobProperties;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.github.job.JobEnum;
import com.github.job.mode.CustomerJob;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 添加任务
     *
     * @param job 任务对象
     */
    public void addJob(CustomerJob job) {

        // 核心配置
        JobCoreConfiguration coreConfiguration = JobCoreConfiguration.newBuilder(job.getJobName(), job.getCron(), job.getShardingTotalCount())
                .shardingItemParameters(job.getShardingItemParameters())
                .description(job.getDescription())
                .failover(job.isFailover())
                .jobParameter(job.getJobParameter())
                .misfire(job.isMisfire())
                .jobProperties(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), job.getJobProperties().getExecutorServiceHandler())
                .jobProperties(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), job.getJobProperties().getJobExceptionHandler())
                .build();


        // 构建任务配置类
        JobTypeConfiguration typeConfig;

        String jobType = job.getJobType();
        if (JobEnum.SIMPLE_JOB.getName().equals(jobType)) {
            typeConfig = new SimpleJobConfiguration(coreConfiguration, job.getJobClass());
        } else if (JobEnum.DATAFLOW_JOB.getName().equals(jobType)) {
            typeConfig = new DataflowJobConfiguration(coreConfiguration, job.getJobClass(), job.isStreamingProcess());
        } else if (JobEnum.SCRIPT_JOB.getName().equals(jobType)) {
            typeConfig = new ScriptJobConfiguration(coreConfiguration, job.getScriptCommandLine());
        } else {
            throw new RuntimeException("不支持的任务类型！");
        }

        // 不同类型的任务配置处理
        LiteJobConfiguration jobConfig = LiteJobConfiguration.newBuilder(typeConfig)
                .overwrite(job.isOverwrite())
                .disabled(job.isDisabled())
                .monitorPort(job.getMonitorPort())
                .monitorExecution(job.isMonitorExecution())
                .maxTimeDiffSeconds(job.getMaxTimeDiffSeconds())
                .jobShardingStrategyClass(job.getJobShardingStrategyClass())
                .reconcileIntervalMinutes(job.getReconcileIntervalMinutes())
                .build();

        List<BeanDefinition> elasticJobListeners = getTargetElasticJobListeners(job);

        // 构建SpringJobScheduler对象来初始化任务
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(SpringJobScheduler.class);
        factory.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        if (JobEnum.SCRIPT_JOB.getName().equals(jobType)) {
            factory.addConstructorArgValue(null);
        } else {
            BeanDefinitionBuilder rdbFactory = BeanDefinitionBuilder.rootBeanDefinition(job.getJobClass());
            factory.addConstructorArgValue(rdbFactory.getBeanDefinition());
        }
        factory.addConstructorArgValue(zookeeperRegistryCenter);
        factory.addConstructorArgValue(jobConfig);

        // 任务执行日志数据源，以名称获取
        if (StringUtils.hasText(job.getEventTraceRdbDataSource())) {
            BeanDefinitionBuilder rdbFactory = BeanDefinitionBuilder.rootBeanDefinition(JobEventRdbConfiguration.class);
            rdbFactory.addConstructorArgValue(job.getEventTraceRdbDataSource());
            factory.addConstructorArgValue(rdbFactory.getBeanDefinition());
        }

        factory.addConstructorArgValue(elasticJobListeners);
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("JobScheduler" + job.getJobName(), factory.getBeanDefinition());
        SpringJobScheduler springJobScheduler = (SpringJobScheduler) applicationContext.getBean("JobScheduler" + job.getJobName());
        springJobScheduler.init();
    }


    /**
     * 获取为Elastic Job中添加的监听器列表
     * @param job 定义的Job任务
     * @return 监听器列表
     */
    private List<BeanDefinition> getTargetElasticJobListeners(CustomerJob job) {
        List<BeanDefinition> result = new ManagedList<>(2);
        String listeners = job.getListener();
        if (StringUtils.hasText(listeners)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(listeners);
            beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
            result.add(beanDefinitionBuilder.getBeanDefinition());
        }

        String distributedListeners = job.getDistributedListener();
        long startedTimeoutMilliseconds = job.getStartedTimeoutMilliseconds();
        long completedTimeoutMilliseconds = job.getCompletedTimeoutMilliseconds();

        return getBeanDefinitions(result, distributedListeners, startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    public List<BeanDefinition> getBeanDefinitions(List<BeanDefinition> result, String distributedListeners, long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        if (StringUtils.hasText(distributedListeners)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(distributedListeners);
            beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
            beanDefinitionBuilder.addConstructorArgValue(startedTimeoutMilliseconds);
            beanDefinitionBuilder.addConstructorArgValue(completedTimeoutMilliseconds);
            result.add(beanDefinitionBuilder.getBeanDefinition());
        }
        return result;
    }

    /**
     * 删除任务
     *
     * @param jobName 任务名称
     * @throws Exception 删除任务异常
     */
    public void removeJob(String jobName) throws Exception {
        CuratorFramework client = zookeeperRegistryCenter.getClient();
        client.delete().deletingChildrenIfNeeded().forPath("/" + jobName);
    }
}
