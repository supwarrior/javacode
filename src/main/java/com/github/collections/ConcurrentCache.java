package com.github.collections;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 康盼Java开发工程师
 */
public final class ConcurrentCache<K,V> {

    private final int size;

    /**
     * 经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收
     */
    private final Map<K,V> eden;

    /**
     * 不常用的对象放入 longTerm，longTerm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
     */
    private final Map<K,V> longTerm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longTerm = new WeakHashMap<>(size);
    }

    /**
     * 当调用 get() 方法时，会先从 eden 区获取，
     * 如果没有找到的话再到 longTerm 获取，当从 longTerm 获取到就把对象放入 eden 中，
     * 从而保证经常被访问的节点不容易被回收
     * @param k
     * @return
     */
    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            synchronized (longTerm) {
                v = this.longTerm.get(k);
            }
            if (v != null) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    /**
     * 当调用 put() 方法时，如果 eden 的大小超过了 size，
     * 那么就将 eden 中的所有对象都放入 longTerm 中，利用虚拟机回收掉一部分不经常使用的对象
     * @param k
     * @param v
     */
    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            synchronized (longTerm) {
                this.longTerm.putAll(this.eden);
            }
            this.eden.clear();
        }
        this.eden.put(k, v);
    }
}
