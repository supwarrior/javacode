package com.github.ddd;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private static ApplicationContext applicationContext;
    private static BeanDefinitionRegistry registry;
    private static ConfigurableListableBeanFactory beanFactory;

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


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        SpringContextUtil.registry = registry;
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringContextUtil.beanFactory = beanFactory;
    }


}