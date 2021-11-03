package com.github.jpa.lock;

import com.github.ddd.businessObject.BaseBO;
import com.github.ddd.factory.GenericCoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ObjectLockManagerImpl implements ObjectLockManager {

    @Autowired
    private  GenericCoreFactory genericCoreFactory;

    @Override
    public <T extends BaseBO> void lock(Class<T> tClass, ObjectIdentifier identifier) {
        Optional.ofNullable(genericCoreFactory.getBO(tClass, identifier.getReferenceKey())).ifPresent(T::lock);
    }
}
