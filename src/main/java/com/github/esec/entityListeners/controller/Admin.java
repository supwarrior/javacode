package com.github.esec.entityListeners.controller;

import com.github.esec.core.SnowflakeIDWorker;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EntityListeners(AuditTrailListener.class) // 前置后置操作
@Entity
@Data
public class Admin {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = SnowflakeIDWorker.STRATEGY_REFERENCE)
    @GeneratedValue(generator = "idGenerator")
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    @Transient
    private String fullName;
}
