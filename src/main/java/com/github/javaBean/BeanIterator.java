package com.github.javabean;

import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @description bean迭代器
 * @author 康盼Java开发工程师
 */
public class BeanIterator<T> implements Iterator<T> {

    private  Class<T> clazz;

    private  ClassLoader classLoader;

    private Enumeration<URL> configs = null;

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
