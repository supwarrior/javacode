package com.github.ddd.businessObject;


import com.github.ddd.domainObject.MainEntity;

public abstract class AbstractProductRequest<T extends MainEntity> extends AbstractBO<T> {

    public AbstractProductRequest(T entity) {
        super(entity);
    }
}
