package com.github.mycim.event;

import com.github.ddd.domainObject.MainEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class EventBaseDO extends MainEntity {

    @Column(
            name = "TRX_ID",
            length = 8
    )
    private String txId;
    @Column(
            name = "EVENT_TIME",
            length = 30
    )
    private String eventTime;
    @Column(
            name = "TRX_USER_ID",
            length = 64
    )
    private String claimUserId;
    @Column(
            name = "TRX_MEMO",
            length = 256
    )
    private String claimMemo;
    @Column(
            name = "EVENT_CREATE_TIME",
            length = 30
    )
    private String eventCreateTime;
    @Column(
            name = "ENTITY_MGR",
            length = 160
    )
    private String objectManager;
}
