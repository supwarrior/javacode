package com.github.mycim.boCell;

import com.github.annotation.Core;
import com.github.ddd.businessObject.AbstractBO;
import com.github.mycim.doCell.CimDataCollectionSpecDO;

@Core
public class CimDataCollectionSpecificationBO extends AbstractBO<CimDataCollectionSpecDO> implements CimDataCollectionSpecification {


    public CimDataCollectionSpecificationBO(CimDataCollectionSpecDO entity) {
        super(entity);
    }

    @Override
    public String getDescription() {
        return this.getEntity().getDescription();
    }
}


