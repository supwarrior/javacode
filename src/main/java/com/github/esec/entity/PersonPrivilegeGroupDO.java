package com.github.esec.entity;

import com.github.esec.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "OMUSER_ACCESSGRP")
@DynamicUpdate
@DynamicInsert
public class PersonPrivilegeGroupDO extends BaseEntity {

    private static final long serialVersionUID = -1212126464647797752L;

    @Column(name = "ACCESS_GRP_KEY", length = 64)
    private String privilegeGroupKey;

    @Column(name = "ACCESS_GRP_RKEY", length = 64)
    private String privilegeGroupReferenceKey;

    @Column(name = "LINK_KEY")
    private String dKey;

    @Column(name = "REFKEY", length = 64)
    private String referenceKey;
}
