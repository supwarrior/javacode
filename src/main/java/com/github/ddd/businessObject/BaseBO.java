package com.github.ddd.businessObject;

/**
 * @author 康盼Java开发工程师
 */
public interface BaseBO {

    /**
     * flush
     */
    default void flush() {
    }

    /**
     * flushMain
     */
    default void flushMain() {

    }

    /**
     * flushAttribute
     */
    default void flushAttribute() {

    }
}
