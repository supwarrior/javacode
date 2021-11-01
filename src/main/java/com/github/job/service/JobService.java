package com.github.job.service;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private ApplicationContext applicationContext;


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
