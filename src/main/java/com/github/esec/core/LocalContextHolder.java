package com.github.esec.core;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.github.esec.entity.User;

import java.util.concurrent.CopyOnWriteArrayList;

public class LocalContextHolder {

    private static class SingletonHolder {
        private static final LocalContextHolder INSTANCE = new LocalContextHolder();
    }

    public static LocalContextHolder getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     * user
     */
    private final TransmittableThreadLocal<User> userLocal;
    /**
     * lock
     */
    private final TransmittableThreadLocal<CopyOnWriteArrayList<LockInstance>> lockLocal;

    private LocalContextHolder() {
        this.userLocal = new TransmittableThreadLocal<>();
        this.lockLocal = new TransmittableThreadLocal<>();
    }
}
