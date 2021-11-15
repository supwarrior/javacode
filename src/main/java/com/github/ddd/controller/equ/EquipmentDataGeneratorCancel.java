package com.github.ddd.controller.equ;

import com.github.common.cons.Response;
import com.github.mycim.controller.equipment.IEquipmentController;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import org.springframework.stereotype.Component;

@Component("EquipmentDataGeneratorCancel")
public class EquipmentDataGeneratorCancel implements IEquipmentController {

    @Override
    public Response equipmentReportDataCollectionReq(EquipmentReportDataCollectionReqParams reportDataCollectionReqParams) {
        return null;
    }
}
