package com.github.jpa.lock;

import com.github.ddd.businessObject.BaseBO;

public interface ObjectLockManager {

    <T extends BaseBO> void lock(Class<T> tClass, ObjectIdentifier identifier);
}
