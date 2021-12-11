package com.github.demo.mainfun.spring;

import com.github.demo.mainfun.factory.DefaultUserFactory;
import com.github.demo.mainfun.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitialization {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanInitialization.class);
        annotationConfigApplicationContext.refresh();
        // 非延迟查找是在上下文启动之前
        // 延迟加载必须在初始化之后
        System.out.println("Spring 应用上下文已启动，将进行初始化...");
        UserFactory userFactory = annotationConfigApplicationContext.getBean(UserFactory.class);
        System.out.println(userFactory.createUser());
        System.out.println("Spring 应用上下文正在关闭...");
        annotationConfigApplicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
        Thread.sleep(5000L);
        System.gc();
        Thread.sleep(5000L);

    }

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    @Lazy
    public UserFactory getUserFactory() {
        return new DefaultUserFactory();
    }
}
