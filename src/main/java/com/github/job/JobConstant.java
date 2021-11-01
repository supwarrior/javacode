package com.github.job;

public class JobConstant {
    /**
     * 配置文件前缀
     */
    public static final String PREFIX = "com.github.elastic.job.";

    /**
     * 扩展异常处理类
     */
    public static final String DEFAULT_JOB_EXCEPTION_HANDLER = "com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler";

    /**
     * 扩展JOB处理线程池类
     */
    public static final String EXECUTOR_SERVICE_HANDLER = "com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler";
}
