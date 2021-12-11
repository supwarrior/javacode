package com.github.demo.mainfun.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("post construct...");
    }

    public void initMethod() {
        System.out.println("init method...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after properties... ");
    }

    public void destroyMethod() {
        System.out.println("destroy method...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy...");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("准备回收...");
    }
}
