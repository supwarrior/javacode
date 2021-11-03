package com.github.jpa.lock;

public interface ObjectLockExecutor {

    void executeLock(String objKey);

    void executeUnlock(String objKey);

    void executeLock(String objKey, String attKey);

    void executeUnlock(String objKey, String attKey);

    default void executeLockAll(String objKey, Iterable<String> attKeys) {
        attKeys.forEach((attkey) -> {
            this.executeLock(objKey, attkey);
        });
    }

    default void executeUnlockAll(String objKey, Iterable<String> attKeys) {
        attKeys.forEach((attkey) -> {
            this.executeUnlock(objKey, attkey);
        });
    }

    void forceUnlockAll();

}
