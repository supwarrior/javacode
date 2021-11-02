package com.github.job.controller;

import com.github.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 删除动态注册的任务（只删除注册中心中的任务信息）
     *
     * @param jobName 任务名称
     */
    @GetMapping("/job/remove/{jobName}")
    public void removeJob(@PathVariable(value = "jobName") String jobName) throws Exception {
        jobService.removeJob(jobName);
    }
}
