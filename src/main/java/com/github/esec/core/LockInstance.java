package com.github.esec.core;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class LockInstance {

    public static final String DB_LOCK_PREFIX = "DB_LOCK";

    public static final String STR_JOIN = ":";

    /**
     * redisson client
     */
    private final RedissonClient redissonClient;

    private RLock lock;

    public LockInstance(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    public void lock(Class<? extends BaseEntity> dbClass, String id) {
        String locKey = DB_LOCK_PREFIX + STR_JOIN + dbClass.getName() + STR_JOIN + id;
        lock(locKey);
    }

    public void lock(String locKey) {
        this.lock = redissonClient.getFairLock(locKey);
        lock.lock();
        LocalContextHolder.getInstance().addLock(this);
    }

    public void unlock() {
        lock.unlock();
    }
}
