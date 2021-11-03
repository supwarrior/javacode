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


