package com.github.jpa.lock;

import com.github.annotation.Component;
import com.github.ddd.businessObject.BaseBO;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ObjectLockMethod implements IObjectLockMethod {

    @Autowired
    private ObjectLockManager lockManager;

    @Override
    public void objectLock(Class<? extends BaseBO> clazz, ObjectIdentifier objectID) {
        lockManager.lock(clazz, objectID);
    }
}
