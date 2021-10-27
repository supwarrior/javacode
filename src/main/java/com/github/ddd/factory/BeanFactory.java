package com.github.ddd.factory;

/**
 * @author 康盼Java开发工程师
 */
public interface BeanFactory {

    /**
     * getBean
     *
     * @param clazz
     * @param args
     * @param <T>
     * @return
     */
    <T> T getBean(Class<T> clazz, Object... args);

    /**
     * getDefaultBeanFactory
     *
     * @return
     */
    static BeanFactory getDefaultBeanFactory() {
        return new DefaultBeanFactory();
    }

}
