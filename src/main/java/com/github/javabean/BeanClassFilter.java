package com.github.javabean;


/**
 * @author 康盼Java开发工程师
 */
@FunctionalInterface
public interface BeanClassFilter<T> {
    /**
     * accept
     *
     * @param clazz
     * @return
     */
    boolean accept(T clazz);
}
