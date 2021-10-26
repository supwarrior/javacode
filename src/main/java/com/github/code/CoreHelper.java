package com.github.code;

import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class CoreHelper {

    public <T> T generateBO(Class<T> boClazz, BaseEntity entity) {
        return (T) BeanFactory.getDefaultBeanFactory().getBean(boClazz, new Object[]{entity});
    }
}