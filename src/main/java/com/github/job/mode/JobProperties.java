package com.github.job.mode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.job.JobConstant;

public class JobProperties {


    /**
     * 用于扩展JOB异常处理类
     */
    @JsonProperty("job_exception_handler")
    private final String jobExceptionHandler = JobConstant.DEFAULT_JOB_EXCEPTION_HANDLER;

    /**
     * 用于扩展JOB处理线程池类
     */
    @JsonProperty("executor_service_handler")
    private final String executorServiceHandler = JobConstant.EXECUTOR_SERVICE_HANDLER;
}
