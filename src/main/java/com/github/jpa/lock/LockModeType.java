package com.github.jpa.lock;

public enum LockModeType {

    /**
     * 乐观读锁
     */
    READ,
    /**
     * 乐观写锁
     */
    WRITE,
    OPTIMISTIC,
    OPTIMISTIC_FORCE_INCREMENT,
    /**
     *  悲观读锁
     */
    PESSIMISTIC_READ,
    /**
     * 悲观写锁
     */
    PESSIMISTIC_WRITE,
    PESSIMISTIC_FORCE_INCREMENT,
    NONE;

    private LockModeType() {
    }
}
