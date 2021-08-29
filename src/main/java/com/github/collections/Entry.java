package com.github.collections;

/**
 * @author 康盼Java开发工程师
 */
public interface Entry<K,V> {

    /**
     * getKey
     *
     * @return K
     */
    K getKey();

    /**
     * getValue
     *
     * @return V
     */
    V getValue();
}
