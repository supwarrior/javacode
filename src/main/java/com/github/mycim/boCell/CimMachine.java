package com.github.mycim.boCell;

import com.github.ddd.businessObject.BaseBO;
import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.dto.EDCDTO;

import java.util.List;

public interface CimMachine extends BaseBO {
    void doSpecCheck(List<EDCDTO.DCItemData> dataItemDataList, ObjectIdentifier specId, EDCDTO.NonLotEdcData nonLotEdcData, CimDataCollectionSpecification spec);
}
