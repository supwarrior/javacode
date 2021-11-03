package com.github.jpa.lock;


import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.ddd.domainObject.MainEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@MasterEntity
@Table(name = "t_lock")
@ToString(callSuper = true)
@IdPrefix(value = "k")
public class LockDO extends MainEntity {
    @Column(name = "OBJ_KEY")
    private String objectKey;
    @Column(name = "ATT_KEY")
    private String attributeKey;
    @Column(name = "LOCK_CREATE_TIME")
    private Timestamp lockCreateTime;
    @Column(name = "LAST_LOCKED_TIME")
    private Timestamp lastLockedTime;
    @Column(name = "LAST_RELEASE_TIME")
    private Timestamp lastReleaseTime;
    @Column(name = "IN_USE_FLAG")
    private Boolean inUseFlag = false;
}
