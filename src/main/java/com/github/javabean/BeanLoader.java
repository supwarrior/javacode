package com.github.javabean;


import java.util.Iterator;
import java.util.Objects;

/**
 * @author 康盼Java开发工程师
 * @description Javabean 的加载器 类似SPI机制
 */
public class BeanLoader<T> implements Iterable<T> {
    /**
     * bean 接口类型
     */
    private final Class<T> clazz;
    /**
     * 类加载器
     */
    private final ClassLoader classLoader;
    /**
     * bean 迭代器
     */
    private BeanIterator<T> beanIterator;

    /**
     * 私有构造
     *
     * @param clazz       接口类型
     * @param classLoader 类加载器
     */
    private BeanLoader(Class<T> clazz, ClassLoader classLoader) {
        this.clazz = Objects.requireNonNull(clazz, "interface cannot be null");
        this.classLoader = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
        this.beanIterator = new BeanIterator(clazz, classLoader);
    }

    /**
     * 实例化
     *
     * @param clazz 接口类型
     * @param <T>   接口
     * @return BeanLoader
     */
    public static <T> BeanLoader<T> load(Class<T> clazz) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new BeanLoader(clazz, classLoader);
    }

    @Override
    public Iterator<T> iterator() {
        return beanIterator;
    }
}
