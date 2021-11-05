# javacode
每一个包对应一个功能领域

需要启动zookeeper
需要启动nacos

nacos配置 javacode.properties

RetryServiceInterceptor.intervalTime = 10000



server.port=8028

spring.banner.location=classpath:template/banner.txt

#hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true

#P6SpyDriver h2
spring.h2.console.settings.web-allow-others=false
#spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver
spring.datasource.url=jdbc:p6spy:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=


spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# default enable auth
boot.login.auth=secure

### 11月份读
http://origin.redisbook.com/


**熟悉 mycim-sentinels 项目中 使用的当当网分布式任务调度框架并自己模拟写了个demo**

1. 通过自定义注解 @JobConfig 及环境变量 Environment 进行 a.任务核心配置 b.任务配置，使用 Spring 的 BeanDefinitionBuilder ,通过构造方法，构建 SpringJobScheduler对象来初始化任务，比如zookeeperRegistryCenter ElasticJobListener 等
2. 增加对应的 service 类,提供 removeJob 等，通过 JobController 对外提供对应的功能

**熟悉 myCIM4.0 实现锁机制原理并自己模拟写个了demo**


1. 项目原代码，举个例子
```java
Outputs.ObjLockModeOut objLockModeOut = objectMethod.objectLockModeGet(objCommon, objLockModeIn);
```

2.项目中提供两种实现方案，使用 JPA 的悲观锁及 redisson 的 RReadWriteLock 锁，可以通过 NACOS 配置 mycim.lock.mode，默认使用 JPA 行锁，SQL打印的时候查看默认加上了 for update
，对应的是 LockModeType 的 PESSIMISTIC_WRITE 悲观写锁（其他事务不能同时读取或写入实体），
涉及表 CimLockDO，对应的 objectKey 就是操作相关 DO entity 对象的 id。

**熟悉  myCIM4.0 分页机制**

**了解 myCIM4.0 配置文件加密方式**

**熟悉 myCIM4.0 项目中使用 bytetcc 分布式事务原理 TCC 并自己写了个简单demo**

项目代码
```java
@Compensable(interfaceClass = IReticleController.class, confirmableKey = "ReticleConfirm", cancellableKey = "ReticleCancel")
```

1. 利用 spring 容器管理的所有单例对象（非懒加载对象）初始化完成之后调用的回调接口去查找这些单例对象是否含有 Compensable 注解，通过 compensable.confirmableKey 和 compensable.cancellableKey() 在 spring 容器中查找对应的实例，在 confirm 类 做第二次提交，失败了在 cancell 类 做回滚



