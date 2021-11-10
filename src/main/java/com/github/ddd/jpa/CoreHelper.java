package com.github.ddd.jpa;

import com.github.ddd.domainObject.BaseEntity;

public interface CoreHelper {
    <E extends BaseEntity> E newEntity(Class<E> entityClazz);
}
