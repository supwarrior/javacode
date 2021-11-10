package com.github.mycim.doCell;

import com.github.annotation.IdPrefix;
import com.github.ddd.domainObject.ChildEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
        name = "OMEQP_EDC"
)
@IdPrefix("OMEQP_EDC")
@Data
public class CimEquipmentEdcDO extends ChildEntity {
    @Column(
            name = "LINK_KEY"
    )
    private String linkKey;
    @Column(
            name = "EDC_PLAN_ID",
            length = 64
    )
    private String edcPlanId;
    @Column(
            name = "EDC_PLAN_RKEY",
            length = 64
    )
    private String edcPlanReferenceKey;
    @Column(
            name = "EDC_SPEC_ID",
            length = 64
    )
    private String edcSpecId;
    @Column(
            name = "EDC_SPEC_RKEY",
            length = 64
    )
    private String edcSpecReferenceKey;

}
