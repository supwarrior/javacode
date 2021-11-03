package com.github.jpa.lock;

import com.github.ddd.businessObject.BaseBO;

public interface IObjectLockMethod {

    void objectLock(Class<? extends BaseBO> clazz, ObjectIdentifier objectID);
}
