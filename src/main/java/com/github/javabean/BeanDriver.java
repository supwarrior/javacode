package com.github.javabean;


import java.util.Map;

/**
 * @author 康盼Java开发工程师
 * @description Bean缓存
 */
public interface BeanDriver {
     Map<String, Object> cache = new BeanCache<>(10);
}
