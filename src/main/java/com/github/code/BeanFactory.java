package com.github.code;

/**
 * @author 康盼Java开发工程师
 */
public interface BeanFactory {

    <T> T getBean(Class<T> requiredType, Object... args) ;

    static BeanFactory getDefaultBeanFactory() {
        return new DefaultBeanFactory();
    }
}