package com.github.javabean;


import java.util.Objects;

/**
 * @description Javabean 的加载器
 * @author 康盼Java开发工程师
 */
public class BeanLoader<T> {
    /**
     * bean存放的位置
     */
    private static final String PREFIX = "META-INF/bean";
    /**
     * bean 接口类型
     */
    private final Class<T> clazz;
    /**
     * 类加载器
     */
    private final ClassLoader classLoader;

    private BeanLoader(Class<T> clazz, ClassLoader classLoader) throws Exception {
        this.clazz = Objects.requireNonNull(clazz,"interface cannot be null");
        this.classLoader = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
    }
}
