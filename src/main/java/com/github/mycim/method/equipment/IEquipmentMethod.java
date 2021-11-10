package com.github.mycim.method.equipment;

import com.github.mycim.dto.Infos;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import com.github.mycim.model.eqp.result.EquipmentDataCollectionSpecResults;

public interface IEquipmentMethod {

    EquipmentDataCollectionSpecResults equipmentDataCollectionReport(
            Infos.ObjCommon objCommon, EquipmentReportDataCollectionReqParams reportDataParams);
}
