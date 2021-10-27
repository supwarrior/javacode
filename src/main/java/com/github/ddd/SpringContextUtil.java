package com.github.ddd;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static final CoreBeanCachePool beanCachePool = CoreBeanCachePool.INSTANCE;

    public static <T> T getSingletonBean(Class<T> clazz) {
        T bean = beanCachePool.getBean(clazz);
        if (null == bean) {
            bean = applicationContext.getBean(clazz);
            beanCachePool.cacheBean(clazz, bean);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}