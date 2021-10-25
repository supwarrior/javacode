package com.github.code;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class ChildEntity extends BaseEntity {

    private static final long serialVersionUID = 6035207218845913302L;

    @Column(name = "REFKEY", length = 64)
    private String referenceKey;

}
