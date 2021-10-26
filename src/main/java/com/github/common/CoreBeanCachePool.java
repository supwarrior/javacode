package com.github.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
     * Log
     */
    private static final Log log = LogFactory.getLog(CoreBeanCachePool.class);

    /**
     * Map 缓存，存储 Class 对象和 Spring 单例对象实例的对应关系
     */
    private static final Map<String, Object> springBeanCache = new ConcurrentHashMap<>(1024);


    /**
     * 缓存 bean 对象实例
     *
     * @param simpleName class 对象的 simpleName ，全部为大写
     * @param instance   对象实例
     * @author zqi
     */
    public void cacheBean(String simpleName, Object instance) {
        springBeanCache.putIfAbsent(simpleName.toUpperCase(Locale.ROOT), instance);
    }

    /**
     * 缓存 bean 对象实例
     *
     * @param requiredType class 对象
     * @param instance     对象实例
     */
    public void cacheBean(Class<?> requiredType, Object instance) {
        if (log.isInfoEnabled()) {
            log.info("Cache bean [" + requiredType.getSimpleName() + "]");
        }
        final String key = requiredType.getSimpleName().toUpperCase(Locale.ROOT);
        springBeanCache.putIfAbsent(key, instance);
    }

    /**
     * 通过指定的 key 和 class 类型，缓存对象实例
     *
     * @param key          指定的 key
     * @param requiredType class 对象
     * @param instance     对象实例
     * @throws RuntimeException 如果指定的对象类型与指定的 instance 类型不匹配，则抛出此异常
     * @author zqi
     */
    public void cacheBean(String key, Class<?> requiredType, Object instance) {
        if (!requiredType.isAssignableFrom(instance.getClass())) {
            throw new RuntimeException("Specific the key of class bean is not match of the specific class type.");
        }
        if (log.isInfoEnabled()) {
            log.info("Cache bean [" + requiredType.getSimpleName() + "]");
        }
        springBeanCache.putIfAbsent(key, instance);
    }


    /**
     * 获取 class 对象实例
     *
     * @param requiredType class 对象
     * @param <T>          类型限定
     * @return 返回指定的 {@link T} 类型的实例对象
     * @author zqi
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        final String key = requiredType.getSimpleName().toUpperCase(Locale.ROOT);
        return (T) springBeanCache.get(key);
    }

    /**
     * 通过指定的 key 和 Class 对象，获取 class 对象实例
     *
     * @param key          指定的 key
     * @param requiredType class 对象
     * @param <T>          类型限定
     * @return 返回指定的 {@link T} 类型的实例对象
     * @throws RuntimeException 如果通过 key 找到的对象实例与指定的 class 类型不匹配，则抛出此异常
     * @author zqi
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String key, Class<T> requiredType) {
        final Object object = springBeanCache.get(key);
        if (null != object) {
            if (requiredType.isAssignableFrom(object.getClass())) {
                return (T) object;
            }
            throw new RuntimeException("Specific the key of class bean is not match of the specific class type.");
        } else {
            return null;
        }
    }

    /**
     * 获取缓存队列中实例的数量
     *
     * @return 实例数量
     * @author zqi
     */
    public int size() {
        return springBeanCache.size();
    }
}