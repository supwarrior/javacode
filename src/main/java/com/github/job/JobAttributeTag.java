package com.github.job;

public class JobAttributeTag {

    /**
     * cron表达式，用于控制作业触发时间
     */
    public static final String CRON = "cron";

    /**
     * 作业分片总数
     */
    public static final String SHARDING_TOTAL_COUNT = "shardingTotalCount";

    /**
     * 分片序列号和参数用等号分隔，多个键值对用逗号分隔
     * 分片序列号从0开始，不可大于或等于作业分片总数
     * 如： 0=a,1=b,2=c
     */
    public static final String SHARDING_ITEM_PARAMETERS = "shardingItemParameters";

    /**
     * 作业自定义参数
     * 可通过传递该参数为作业调度的业务方法传参，用于实现带参数的作业
     * 例：每次获取的数据量、作业实例从数据库读取的主键等
     */
    public static final String JOB_PARAMETER = "jobParameter";

    /**
     * 监控作业运行时状态
     */
    public static final String MONITOR_EXECUTION = "monitorExecution";

    /**
     * 作业监控端口
     */
    public static final String MONITOR_PORT = "monitorPort";

    /**
     * 是否开启失效转移
     */
    public static final String FAILOVER = "failover";

    /**
     * 最大允许的本机与注册中心的时间误差秒数
     */
    public static final String MAX_TIME_DIFF_SECONDS = "maxTimeDiffSeconds";

    /**
     * 是否开启错过任务重新执行
     */
    public static final String MISFIRE = "misfire";

    /**
     * 作业分片策略实现类全路径
     * 默认使用平均分配策略
     */
    public static final String JOB_SHARDING_STRATEGY_CLASS = "jobShardingStrategyClass";

    /**
     * 作业描述信息
     */
    public static final String DESCRIPTION = "description";

    /**
     * 作业是否禁止启动
     * 可用于部署作业时，先禁止启动，部署结束后统一启动
     */
    public static final String DISABLED = "disabled";

    /**
     * 本地配置是否可覆盖注册中心配置
     * 如果可覆盖，每次启动作业都以本地配置为准
     */
    public static final String OVERWRITE = "overwrite";

    /**
     * 前置后置任务监听实现类，需实现ElasticJobListener接口
     */
    public static final String LISTENER = "listener";

    /**
     * 前置后置任务分布式监听实现类，需继承AbstractDistributeOnceElasticJobListener类
     */
    public static final String DISTRIBUTED_LISTENER = "distributedListener";

    /**
     * 最后一个作业执行前的执行方法的超时时间
     * 单位：毫秒
     */
    public static final String DISTRIBUTED_LISTENER_STARTED_TIMEOUT_MILLISECONDS = "startedTimeoutMilliseconds";

    /**
     * 最后一个作业执行后的执行方法的超时时间
     * 单位：毫秒
     */
    public static final String DISTRIBUTED_LISTENER_COMPLETED_TIMEOUT_MILLISECONDS = "completedTimeoutMilliseconds";

    /**
     * 扩展作业处理线程池类
     */
    public static final String EXECUTOR_SERVICE_HANDLER = "executorServiceHandler";

    /**
     * 扩展异常处理类
     */
    public static final String JOB_EXCEPTION_HANDLER = "jobExceptionHandler";

    /**
     * 作业事件追踪的数据源Bean引用
     */
    public static final String EVENT_TRACE_RDB_DATA_SOURCE = "eventTraceRdbDataSource";

    /**
     * 修复作业服务器不一致状态服务调度间隔时间，配置为小于1的任意值表示不执行修复
     * 单位：分钟
     */
    public static final String RECONCILE_INTERVAL_MINUTES = "reconcileIntervalMinutes";

    /**
     * 脚本型作业执行命令行
     */
    public static final String SCRIPT_COMMAND_LINE = "scriptCommandLine";

    /**
     * 是否流式处理数据
     * 如果流式处理数据, 则fetchData不返回空结果将持续执行作业
     * 如果非流式处理数据, 则处理数据完成后作业结束
     */
    public static final String STREAMING_PROCESS = "streamingProcess";

}
