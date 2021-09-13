package com.github.juc;

import java.lang.ref.WeakReference;

/**
 * @author 康盼Java开发工程师
 */
public class ThreadLocal<T> {

    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
           ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return null;
    }

    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            map.set(this, value);
        } else {
            createMap(t, value);
        }
    }

    private void createMap(Thread t, T value) {
    }

    static class ThreadLocalMap {

        static class Entry extends WeakReference<ThreadLocal<?>> {
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
        private ThreadLocalMap.Entry getEntry(ThreadLocal<?> key) {
          return null;
        }

        private void set(ThreadLocal<?> key, Object value) {

        }
    }

    ThreadLocalMap getMap(Thread t) {
        return null;
    }
}
