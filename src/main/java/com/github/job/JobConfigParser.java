package com.github.job;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.executor.handler.JobProperties;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class JobConfigParser implements ApplicationContextAware {

    private Environment environment;

    /**
     * 任务类型名称
     */
    private final List<String> jobTypeNameList = JobEnum.jobNameList();


    /**
     * ZK注解中心
     */
    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.environment = applicationContext.getEnvironment();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(JobConfig.class);
        for (Object confBean : beanMap.values()) {
            Class<?> clz = confBean.getClass();
            String jobTypeName = clz.getInterfaces()[0].getSimpleName();
            if (jobTypeNameList.contains(jobTypeName)) {
                JobConfig conf = AnnotationUtils.findAnnotation(clz, JobConfig.class);
                if (Objects.isNull(conf)) {
                    continue;
                }

                // 获取注解中的各项配置参数
                String jobClass = clz.getName();
                String jobName = conf.name();
                String cron =
                        getEnvironmentStringValue(jobName, JobAttributeTag.CRON, conf.cron());
                String shardingItemParameters =
                        getEnvironmentStringValue(jobName, JobAttributeTag.SHARDING_ITEM_PARAMETERS, conf.shardingItemParameters());
                String description =
                        getEnvironmentStringValue(jobName, JobAttributeTag.DESCRIPTION, conf.description());
                String jobParameter =
                        getEnvironmentStringValue(jobName, JobAttributeTag.JOB_PARAMETER, conf.jobParameter());
                String jobExceptionHandler =
                        getEnvironmentStringValue(jobName, JobAttributeTag.JOB_EXCEPTION_HANDLER, conf.jobExceptionHandler());
                String executorServiceHandler =
                        getEnvironmentStringValue(jobName, JobAttributeTag.EXECUTOR_SERVICE_HANDLER, conf.executorServiceHandler());
                String jobShardingStrategyClass =
                        getEnvironmentStringValue(jobName, JobAttributeTag.JOB_SHARDING_STRATEGY_CLASS, conf.jobShardingStrategyClass());
                String eventTraceRdbDataSource =
                        getEnvironmentStringValue(jobName, JobAttributeTag.EVENT_TRACE_RDB_DATA_SOURCE, conf.eventTraceRdbDataSource());
                String scriptCommandLine =
                        getEnvironmentStringValue(jobName, JobAttributeTag.SCRIPT_COMMAND_LINE, conf.scriptCommandLine());

                boolean failover =
                        getEnvironmentBooleanValue(jobName, JobAttributeTag.FAILOVER, conf.failover());
                boolean misfire =
                        getEnvironmentBooleanValue(jobName, JobAttributeTag.MISFIRE, conf.misfire());
                boolean overwrite =
                        getEnvironmentBooleanValue(jobName, JobAttributeTag.OVERWRITE, conf.overwrite());
                boolean disabled =
                        getEnvironmentBooleanValue(jobName, JobAttributeTag.DISABLED, conf.disabled());
                boolean monitorExecution =
                        getEnvironmentBooleanValue(jobName, JobAttributeTag.MONITOR_EXECUTION, conf.monitorExecution());
                boolean streamingProcess =
                        getEnvironmentBooleanValue(jobName, JobAttributeTag.STREAMING_PROCESS, conf.streamingProcess());

                int shardingTotalCount =
                        getEnvironmentIntValue(jobName, JobAttributeTag.SHARDING_TOTAL_COUNT, conf.shardingTotalCount());
                int monitorPort =
                        getEnvironmentIntValue(jobName, JobAttributeTag.MONITOR_PORT, conf.monitorPort());
                int maxTimeDiffSeconds =
                        getEnvironmentIntValue(jobName, JobAttributeTag.MAX_TIME_DIFF_SECONDS, conf.maxTimeDiffSeconds());
                int reconcileIntervalMinutes =
                        getEnvironmentIntValue(jobName, JobAttributeTag.RECONCILE_INTERVAL_MINUTES, conf.reconcileIntervalMinutes());


                // 1.任务核心配置
                JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobName, cron, shardingTotalCount)
                        .shardingItemParameters(shardingItemParameters)
                        .description(description)
                        .failover(failover)
                        .jobParameter(jobParameter)
                        .misfire(misfire)
                        .jobProperties(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), jobExceptionHandler)
                        .jobProperties(JobProperties.JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), executorServiceHandler)
                        .build();

                // 2.任务类型配置
                JobTypeConfiguration typeConfig;

                if (JobEnum.SIMPLE_JOB.getName().equals(jobTypeName)) {
                    typeConfig = new SimpleJobConfiguration(coreConfig, jobClass);
                } else if (JobEnum.DATAFLOW_JOB.getName().equals(jobTypeName)) {
                    typeConfig = new DataflowJobConfiguration(coreConfig, jobClass, streamingProcess);
                } else if (JobEnum.SCRIPT_JOB.getName().equals(jobTypeName)) {
                    typeConfig = new ScriptJobConfiguration(coreConfig, scriptCommandLine);
                } else {
                    throw new RuntimeException("Job Type Configuration is null");
                }


                // 3.Lite任务配置
                LiteJobConfiguration jobConfig = LiteJobConfiguration.newBuilder(typeConfig)
                        .overwrite(overwrite)
                        .disabled(disabled)
                        .monitorPort(monitorPort)
                        .monitorExecution(monitorExecution)
                        .maxTimeDiffSeconds(maxTimeDiffSeconds)
                        .jobShardingStrategyClass(jobShardingStrategyClass)
                        .reconcileIntervalMinutes(reconcileIntervalMinutes)
                        .build();

                // 4.构建SpringJobScheduler对象来初始化任务 通过构造方法注入
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SpringJobScheduler.class);
                beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
                beanDefinitionBuilder.addConstructorArgValue(confBean);
                beanDefinitionBuilder.addConstructorArgValue(zookeeperRegistryCenter);
                beanDefinitionBuilder.addConstructorArgValue(jobConfig);
                List<BeanDefinition> elasticJobListeners = getTargetElasticJobListeners(conf);
                beanDefinitionBuilder.addConstructorArgValue(elasticJobListeners);

                DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
                defaultListableBeanFactory.registerBeanDefinition(jobName + "JobScheduler", beanDefinitionBuilder.getBeanDefinition());
                SpringJobScheduler springJobScheduler = (SpringJobScheduler) applicationContext.getBean(jobName + "JobScheduler");
                springJobScheduler.init();
                log.info("{} {} init success", jobName, jobClass);
            }
        }
    }


    /**
     * 获取Job的监听器列表
     * 通过构造函数注入 JobAttributeTag.LISTENER 和 JobAttributeTag.DISTRIBUTED_LISTENER 两种 ElasticJobListener
     * 然后再通过构造函数注入 SpringJobScheduler
     * @see com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler#SpringJobScheduler(ElasticJob, CoordinatorRegistryCenter, LiteJobConfiguration, ElasticJobListener...) 
     *
     *
     * @param conf 包含配置项的Job注解类
     * @return 监听器列表
     */
    private List<BeanDefinition> getTargetElasticJobListeners(JobConfig conf) {
        List<BeanDefinition> result = new ManagedList<>(2);
        String listeners = getEnvironmentStringValue(conf.name(), JobAttributeTag.LISTENER, conf.listener());
        if (StringUtils.hasText(listeners)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(listeners);
            beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
            result.add(beanDefinitionBuilder.getBeanDefinition());
        }

        String distributedListeners =
                getEnvironmentStringValue(conf.name(), JobAttributeTag.DISTRIBUTED_LISTENER, conf.distributedListener());
        long startedTimeoutMilliseconds =
                getEnvironmentLongValue(conf.name(), JobAttributeTag.DISTRIBUTED_LISTENER_STARTED_TIMEOUT_MILLISECONDS, conf.startedTimeoutMilliseconds());
        long completedTimeoutMilliseconds =
                getEnvironmentLongValue(conf.name(), JobAttributeTag.DISTRIBUTED_LISTENER_COMPLETED_TIMEOUT_MILLISECONDS, conf.completedTimeoutMilliseconds());

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
     * 获取配置中的任务属性字符串值，environment没有就用注解中的值
     *
     * @param jobName      任务名称
     * @param fieldName    属性名称
     * @param defaultValue 默认值
     * @return 属性值
     */
    private String getEnvironmentStringValue(String jobName, String fieldName, String defaultValue) {
        String key = JobConstant.PREFIX + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return value;
        }
        return defaultValue;
    }

    /**
     * 获取配置中的任务属性布尔值，environment没有就用注解中的值
     *
     * @param jobName      任务名称
     * @param fieldName    属性名称
     * @param defaultValue 默认值
     * @return 属性值
     */
    private boolean getEnvironmentBooleanValue(String jobName, String fieldName, boolean defaultValue) {
        String key = JobConstant.PREFIX + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    /**
     * 获取配置中的任务属性整数值，environment没有就用注解中的值
     *
     * @param jobName      任务名称
     * @param fieldName    属性名称
     * @param defaultValue 默认值
     * @return 属性值
     */
    private int getEnvironmentIntValue(String jobName, String fieldName, int defaultValue) {
        String key = JobConstant.PREFIX + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return Integer.parseInt(value);
        }
        return defaultValue;
    }

    /**
     * 获取配置中的任务属性长整型值，environment没有就用注解中的值
     *
     * @param jobName      任务名称
     * @param fieldName    属性名称
     * @param defaultValue 默认值
     * @return 属性值
     */
    private long getEnvironmentLongValue(String jobName, String fieldName, long defaultValue) {
        String key = JobConstant.PREFIX + jobName + "." + fieldName;
        String value = environment.getProperty(key);
        if (StringUtils.hasText(value)) {
            return Long.parseLong(value);
        }
        return defaultValue;
    }
}
