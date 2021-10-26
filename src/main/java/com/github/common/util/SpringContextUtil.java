package com.github.common.util;

import com.github.common.CoreBeanCachePool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private static ApplicationContext applicationContext;
    private static BeanDefinitionRegistry registry;
    private static ConfigurableListableBeanFactory beanFactory;

    private static final CoreBeanCachePool beanCachePool = CoreBeanCachePool.INSTANCE;

    @Override
    public void setApplicationContext(ApplicationContext pApplicationContext) throws BeansException {
        SpringContextUtil.applicationContext = pApplicationContext;
    }

    /**
     * getApplicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static BeanDefinitionRegistry getBeanRegistry() {
        return registry;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        SpringContextUtil.registry = registry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringContextUtil.beanFactory = beanFactory;
    }

    /**
     * 通过指定的 Class 对象，获取实例
     * <p> <b>注意：</b> 单例Bean获取，使用 {@link SpringContextUtil#getSingletonBean(Class)}
     *
     * @param clazz class 对象
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     * @see SpringContextUtil#getSingletonBean(Class) 单例对象获取
     * @see SpringContextUtil#getPrototypeBean(Class) 多例对象获取
     */
    @Deprecated
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }


    /**
     * 通过指定的 Class 对象，获取单例Bean的实例。
     *
     * <p> 先从缓存池中获取对象，如果为空并且从 SpringContext 中获取到结果，返回同时放入缓存池中。
     *
     * @param clazz class 对象
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     */
    public static <T> T getSingletonBean(Class<T> clazz) {
        T bean = beanCachePool.getBean(clazz);
        if (null == bean) {
            bean = applicationContext.getBean(clazz);
            beanCachePool.cacheBean(clazz, bean);
        }
        return bean;
    }

    /**
     * 通过指定的 Class 对象，获取多例Bean的实例。
     *
     * @param clazz class 对象
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     */
    public static <T> T getPrototypeBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过指定的 Class 对象和参数（调用有参构造函数），获取实例
     *
     * <p> 单例和多例的对象都可获取
     *
     * @param clazz class 对象
     * @param args  参数
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     */
    public static <T> T getBean(Class<T> clazz, Object... args) {
        return applicationContext.getBean(clazz, args);
    }

    /**
     * 通过指定的名称和 Class 对象，获取实例
     * <p> <b>注意：</b>只有单例 bean 才能调用此方法
     * todo 后续需要优化，是否需要判断 class 对象是单例还是多例
     *
     * @param name  指定的名称
     * @param clazz class 对象
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     * @see SpringContextUtil#getSingletonBean(String, Class) 单例对象获取
     * @see SpringContextUtil#getPrototypeBean(String, Class) 多例对象获取
     */
    @Deprecated
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 通过指定的名称和 Class 对象，获取单例对象实例
     *
     * <p> 先从缓存池中获取对象，如果为空并且从 SpringContext 中获取到结果，返回同时放入缓存池中。
     *
     * @param name  指定的名称
     * @param clazz class 对象
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     */
    public static <T> T getSingletonBean(String name, Class<T> clazz) {
        T bean = beanCachePool.getBean(name, clazz);
        if (null == bean) {
            bean = applicationContext.getBean(name, clazz);
            beanCachePool.cacheBean(name, clazz, bean);
        }
        return bean;
    }

    /**
     * 通过指定的名称和 Class 对象，获取多例对象实例
     *
     * @param name  指定的名称
     * @param clazz class 对象
     * @param <T>   类型限定
     * @return 对象实例
     * @author zqi
     */
    public static <T> T getPrototypeBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public static <T> Optional<T> getNullableBean(Class<T> clz) {
        try {
            return Optional.of(getSingletonBean(clz));
        } catch (NoSuchBeanDefinitionException e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> getNullableBean(String name, Class<T> clz) {
        try {
            return Optional.of(getSingletonBean(name, clz));
        } catch (NoSuchBeanDefinitionException e) {
            return Optional.empty();
        }
    }

    @Deprecated
    public static Class<?> getType(String name) {
        return getApplicationContext().getType(name);
    }

    /**
     * 根据指定的接口，获取实现该接口所有的在 SpringContext 中的实例对象
     *
     * @param type 指定的接口
     * @param <T>  限定类型
     * @return Bean 集合
     * @author zqi
     */
    public static <T> Map<String, T> getBeaOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 根据指定的注解获取SpringContext中的实例对象
     *
     * @param annotation 指定的注解
     * @return Bean 集合
     * @author zqi
     */
    public static Map<String, Object> getBeanOfAnnotation(Class<? extends Annotation> annotation) {
        return applicationContext.getBeansWithAnnotation(annotation);
    }
}