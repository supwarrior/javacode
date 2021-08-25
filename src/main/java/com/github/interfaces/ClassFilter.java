package com.github.interfaces;


/**
 * @author 康盼Java开发工程师
 */
@FunctionalInterface
public interface ClassFilter<T> {
    /**
     * accept
     *
     * @param clazz
     * @return
     */
    boolean accept(T clazz);
}
