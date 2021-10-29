package com.github.javabean;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
@Slf4j
public class BeanCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public BeanCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.CACHE_SIZE = cacheSize;
    }

    /**
     * LRU原理
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        boolean flag = size() > CACHE_SIZE;
        Object key = eldest.getKey();
        if (flag) {
            log.info("清除边界:{}",key);
        } else {
            log.info("不清除边界:{}",key);
        }
        return flag;
    }
}
