package com.github.mycim.service.equipment;

import com.github.mycim.dto.Infos;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import com.github.mycim.model.eqp.result.EquipmentDataCollectionReportResults;

import java.util.List;

public interface IEquipmentService {

    List<EquipmentDataCollectionReportResults> sxEqpReportDataCollectionReq(
            Infos.ObjCommon objCommon, EquipmentReportDataCollectionReqParams reportDataParams);
}
