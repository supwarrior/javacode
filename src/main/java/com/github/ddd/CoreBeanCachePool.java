package com.github.ddd;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 康盼Java开发工程师
 */
public enum CoreBeanCachePool {

    /**
     * 获取 CoreBeanCache 实例
     */
    INSTANCE;

    /**
     * Map 缓存，存储 Class 对象和 Spring 单例对象实例的对应关系
     */
    private static final Map<String, Object> springBeanCache = new ConcurrentHashMap<>(1024);

    /**
     * 缓存 bean 对象实例
     */
    public void cacheBean(Class<?> requiredType, Object instance) {
        final String key = requiredType.getSimpleName().toUpperCase(Locale.ROOT);
        springBeanCache.putIfAbsent(key, instance);
    }

    /**
     * 获取 class 对象实例
     */
    public <T> T getBean(Class<T> requiredType) {
        final String key = requiredType.getSimpleName().toUpperCase(Locale.ROOT);
        return (T) springBeanCache.get(key);
    }
}
