package com.github.jpa.lock;

public interface LogicLockExecutor {

    void obtainLockForEntity(String objKey);

    void obtainLockForAttribute(String objKey, String attKey);

    void releaseLockForEntity(String objKey);

    void releaseLockForAttribute(String objKey, String attKey);
}
