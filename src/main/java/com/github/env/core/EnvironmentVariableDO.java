package com.github.env.core;


import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import com.github.ddd.domainObject.MainEntity;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ENV")
@IdPrefix("evn")
@MasterEntity
@Data
public class EnvironmentVariableDO extends MainEntity {

    @Column(name = "ENV_ID", length = 64)
    @NamedIdentifier
    private String environmentVariableID;

    @Column(name = "ENV_VALUE", length = 256)
    private String environmentVariableValue;

    @Column(name = "DESCRIPTION", length = 512)
    private String description;
}
