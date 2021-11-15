package com.github.esec.entityListeners.controller;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@EntityListeners(AuditTrailListener.class)
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class Admin {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    @Transient
    private String fullName;
}
