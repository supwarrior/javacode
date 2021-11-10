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
        name = "OMEDCSPEC"
)
@IdPrefix("OMEDCSPEC")
@MasterEntity
@Data
public class CimDataCollectionSpecDO extends MainEntity {

    @Column(
            name = "EDC_SPEC_ID",
            length = 64
    )
    @NamedIdentifier
    private String dataCollectionSpecID;
    @Column(
            name = "VER_ID",
            length = 64
    )
    private String versionID;
    @Column(
            name = "EDC_PLAN_ID",
            length = 64
    )
    private String dataCollectionDefID;
    @Column(
            name = "EDC_PLAN_RKEY",
            length = 64
    )
    private String dataCollectionDefObj;
    @Column(
            name = "DESCRIPTION",
            length = 128
    )
    private String description;
    private Boolean whitelag;
    @Column(
            name = "DOC_CATEGORY",
            length = 64
    )
    private String fpcategory;
    @Column(
            name = "COLL_USE_TYPE",
            length = 64
    )
    private String collectionUseType;
}
