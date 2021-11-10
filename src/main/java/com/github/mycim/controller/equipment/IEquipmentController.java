package com.github.mycim.controller.equipment;

import com.github.common.cons.Response;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;

public interface IEquipmentController {

    Response equipmentReportDataCollectionReq(EquipmentReportDataCollectionReqParams reportDataCollectionReqParams);

}
