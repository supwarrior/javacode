package com.github.mycim.boCell;

import com.github.ddd.businessObject.BaseBO;
import com.github.mycim.dto.EDCDTO;

import java.util.List;

public interface CimDataCollectionDefinition extends BaseBO {

    String getDescription();

    void doValueConversion(List<EDCDTO.DCItemData> dataItemDataList);
}
