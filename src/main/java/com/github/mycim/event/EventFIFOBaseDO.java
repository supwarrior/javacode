package com.github.mycim.event;

import com.github.ddd.domainObject.ChildEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class EventFIFOBaseDO extends ChildEntity {
    @Column(
            name = "LINK_KEY",
            length = 100
    )
    private String dKey;
    @Column(
            name = "RESP_SENTINEL",
            length = 20
    )
    private String watchdogName;
    @Column(
            name = "EVENT_TIME",
            length = 30
    )
    private String eventTime;
    @Column(
            name = "EVENT_RKEY",
            length = 135
    )
    private String eventObj;
}
