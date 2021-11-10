package com.github.mycim.event;


import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(
        name = "OMEVNLEDCDATA"
)
@IdPrefix("OMEVNLEDCDATA")
@MasterEntity
@Data
public class CimEventNonLotEdcDO extends EventBaseDO {

    @Column(
            name = "JOIN_ID",
            length = 64
    )
    private String joinObjectId;
    @Column(
            name = "JOIN_RKEY",
            length = 64
    )
    private String joinObjectRefKey;
    @Column(
            name = "JOIN_TYPE",
            length = 64
    )
    private String joinObjectType;
    @Column(
            name = "EDC_PLAN_ID",
            length = 64
    )
    private String edcPlanId;
    @Column(
            name = "EDC_PLAN_RKEY",
            length = 64
    )
    private String edcPlanRefKey;
    @Column(
            name = "EDC_PLAN_DESC",
            length = 128
    )
    private String edcPlanDesc;
    @Column(
            name = "EDC_SPEC_ID",
            length = 64
    )
    private String edcSpecId;
    @Column(
            name = "EDC_SPEC_RKEY",
            length = 64
    )
    private String edcSpecRefKey;
    @Column(
            name = "EDC_SPEC_DESC",
            length = 128
    )
    private String edcSpecDesc;
    @Column(
            name = "REPORT_TIME"
    )
    private Timestamp reportTime;
    @Column(
            name = "REPORT_TYPE",
            length = 64
    )
    private String reportType;

}
