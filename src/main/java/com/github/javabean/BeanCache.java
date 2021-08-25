package com.github.javabean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
public class BeanCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    public BeanCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.CACHE_SIZE = cacheSize;
    }

    /**
     * map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
     * LRU原理
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        System.out.println(eldest.getKey());
        return size() > CACHE_SIZE;
    }
}
