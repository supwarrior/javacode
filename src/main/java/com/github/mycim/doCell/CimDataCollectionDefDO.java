package com.github.mycim.doCell;


import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import com.github.ddd.domainObject.MainEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
        name = "OMEDCPLAN"
)
@IdPrefix("OMEDCPLAN")
@MasterEntity
@Data
public class CimDataCollectionDefDO extends MainEntity {

    @Column(
            name = "EDC_PLAN_ID",
            length = 64
    )
    @NamedIdentifier
    private String dataCollectionDefID;
    @Column(
            name = "VER_ID",
            length = 64
    )
    private String versionId;
    @Column(
            name = "DESCRIPTION",
            length = 128
    )
    private String description;
    private String dcType;
    private Boolean whiteFlag;
    @Column(
            name = "DOC_CATEGORY",
            length = 64
    )
    private String fpcCategory;
    private String dcSettingType;
    @Column(
            name = "COLL_USE_TYPE",
            length = 64
    )
    private String collectionUseType;
}
