package com.github.ddd.domainObject;

import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@MasterEntity
@Table(name = "t_sub_person")
@ToString(callSuper = true)
@IdPrefix(value = "k")
public class SubPersonDO extends ChildEntity{

    @Column(name = "USER_ID", length = 64, unique = true)
    @NamedIdentifier
    private String userID;

    @Column(name = "ORG_ID", length = 64)
    private String companyID;

    @Column(name = "TEL_CONTACT_NO", length = 32)
    private String phoneNO;

    @Column(name = "EMAIL_ID", length = 64)
    private String emailAddress;

    @Column(name = "PASSWD", length = 64)
    private String password;
}
