package com.github.resource;


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
