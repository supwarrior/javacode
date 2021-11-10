package com.github.mycim.doCell;

import com.github.annotation.IdPrefix;
import com.github.ddd.domainObject.ChildEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
        name = "OMEDCSPEC_ITEM"
)
@IdPrefix("OMEDCSPEC_ITEM")
@Data
public class CimDataCollectionSpecItemDO  extends ChildEntity {

    @Column(
            name = "LINK_KEY"
    )
    private String linkKey;

    @Column(
            name = "SPEC_ITEM_NAME",
            length = 64
    )
    private String dataCollectionItemName;
    @Column(
            name = "SCRN_UP_FLAG"
    )
    private Boolean screenUpperRequired;
    @Column(
            name = "SCRN_UP_LIMIT"
    )
    private Double screenUpperLimit;
    @Column(
            name = "SCRN_UP_ACT",
            length = 1024
    )
    private String screenUpperActions;
    @Column(
            name = "SCRN_LO_FLAG"
    )
    private Boolean screenLowerRequired;
    @Column(
            name = "SCRN_LO_LIMIT"
    )
    private Double screenLowerLimit;
    @Column(
            name = "SCRN_LO_ACT",
            length = 1024
    )
    private String screenLowerActions;
    @Column(
            name = "SPEC_UP_FLAG"
    )
    private Boolean specUpperRequired;
    @Column(
            name = "SPEC_UP_LIMIT"
    )
    private Double specUpperLimit;
    @Column(
            name = "SPEC_UP_ACT",
            length = 1024
    )
    private String specUpperActions;
    @Column(
            name = "SPEC_LO_FLAG"
    )
    private Boolean specLowerRequired;
    @Column(
            name = "SPEC_LO_LIMIT"
    )
    private Double specLowerLimit;
    @Column(
            name = "SPEC_LO_ACT",
            length = 1024
    )
    private String specLowerActions;
    @Column(
            name = "CTRL_UP_FLAG"
    )
    private Boolean controlUpperRequired;
    @Column(
            name = "CTRL_UP_LIMIT"
    )
    private Double controlUpperLimit;
    @Column(
            name = "CTRL_UP_ACT",
            length = 1024
    )
    private String controlUpperActions;
    @Column(
            name = "CTRL_LO_FLAG"
    )
    private Boolean controlLowerRequired;
    @Column(
            name = "CTRL_LO_LIMIT"
    )
    private Double controlLowerLimit;
    @Column(
            name = "CTRL_LO_ACT",
            length = 1024
    )
    private String controlLowerActions;
    @Column(
            name = "SPEC_ITEM_TARGET"
    )
    private Double dataCollectionItemTarget;
    @Column(
            name = "SPEC_ITEM_TAG",
            length = 1024
    )
    private String dataCollectionItemTag;
    @Column(
            name = "EDC_SPEC_GROUP",
            length = 64
    )
    private String dataCollectionSpecGroup;
}
