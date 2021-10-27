package com.github.ddd.domainObject;

import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据库表结构一一对应，通过 DAO 层向上传输数据源对象
 *
 * @author 康盼Java开发工程师
 */
@Entity
@Data
@MasterEntity
@Table(name = "t_person")
@ToString(callSuper = true)
@IdPrefix(value = "k")
public class PersonDO extends MainEntity {

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