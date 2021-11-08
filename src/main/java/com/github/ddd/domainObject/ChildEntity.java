package com.github.ddd.domainObject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class ChildEntity extends BaseEntity {
    @Column(
            name = "REFKEY",
            length = 64
    )
    private String referenceKey;
}
