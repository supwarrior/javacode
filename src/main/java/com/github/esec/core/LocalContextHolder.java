package com.github.esec.core;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.github.esec.entity.User;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocalContextHolder {

    /**
     * https://gitee.com/alibaba-projects/transmittable-thread-local#/alibaba-projects/transmittable-thread-local/blob/master/docs/requirement-scenario.md
     */
    private final TransmittableThreadLocal<User> userLocal;

    private final TransmittableThreadLocal<CopyOnWriteArrayList<LockInstance>> lockLocal;

    private LocalContextHolder() {
        this.userLocal = new TransmittableThreadLocal<>();
        this.lockLocal = new TransmittableThreadLocal<>();
    }

    public static LocalContextHolder getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final LocalContextHolder INSTANCE = new LocalContextHolder();
    }

    public User getContext() {
        return Optional.ofNullable(userLocal.get()).orElse(new User());
    }
    public void setContext(User user) {
        userLocal.set(user);
    }

    public void clear() {
        userLocal.remove();
        clearLock();
    }

    public void clearLock() {
        CopyOnWriteArrayList<LockInstance> rLocks = Optional.ofNullable(lockLocal.get()).orElse(new CopyOnWriteArrayList<>());
        if (CollectionUtils.isNotEmpty(rLocks)) {
            rLocks.forEach(LockInstance::unlock);
            lockLocal.remove();
        }
    }

    public void addLock(LockInstance lockInstance) {
        CopyOnWriteArrayList<LockInstance> rLocks = Optional.ofNullable(lockLocal.get()).orElse(new CopyOnWriteArrayList<>());
        rLocks.add(lockInstance);
        lockLocal.set(rLocks);
    }
}
