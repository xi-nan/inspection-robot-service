package com.boot.commons.core.lock;

public enum LockType {
    /**
     * 可重入锁
     */
    RE_ENTRANT_LOCK,

    /**
     * 公平锁
     */
    FAIR_LOCK,

    /**
     * 读锁
     */
    READ_LOCK,

    /**
     * 写锁
     */
    WRITE_LOCK
}
