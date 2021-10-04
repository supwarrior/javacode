package com.github.javabean;

/**
 * @author 康盼Java开发工程师
 */
public interface BeanLifecycle {

    /**
     * 初始化方法
     */
    void initDataMethod();

    /**
     * 销毁方法
     */
    void destroyDataMethod();
}
