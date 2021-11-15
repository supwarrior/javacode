package com.github.postProcessEvent.entity;

import com.github.ddd.domainObject.BaseEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NonRuntimeEntity extends BaseEntity {
    private static final long serialVersionUID = -7231734199685477300L;

    public NonRuntimeEntity() {
    }
}
