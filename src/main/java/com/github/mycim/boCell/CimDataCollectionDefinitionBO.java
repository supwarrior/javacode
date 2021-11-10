package com.github.mycim.boCell;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.github.annotation.Core;
import com.github.ddd.businessObject.AbstractBO;
import com.github.mycim.doCell.CimDataCollectionDefDO;
import com.github.mycim.dto.EDCDTO;

import java.util.List;
import java.util.Optional;

@Core
public class CimDataCollectionDefinitionBO extends AbstractBO<CimDataCollectionDefDO> implements CimDataCollectionDefinition {


    public CimDataCollectionDefinitionBO(CimDataCollectionDefDO entity) {
        super(entity);
    }

    @Override
    public String getDescription() {
        return this.getEntity().getDescription();
    }


    @Override
    public void doValueConversion(List<EDCDTO.DCItemData> dataItemDataList) {
        Optional.ofNullable(dataItemDataList).ifPresent(list -> {
            list.parallelStream().forEach(dcItemData -> {
                // 值处理
                if (StringUtils.equals(dcItemData.getItemType(), "User Func")) {
                    dcItemData.setSpecCheckResult("#");
                }
            });
        });
    }
}
