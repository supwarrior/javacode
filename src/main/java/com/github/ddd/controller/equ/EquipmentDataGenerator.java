package com.github.ddd.controller.equ;

import com.github.common.cons.Response;
import com.github.ddd.BaseCore;
import com.github.mycim.controller.equipment.IEquipmentController;
import com.github.mycim.model.eqp.params.EquipmentReportDataCollectionReqParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("EquipmentDataGenerator")
public class EquipmentDataGenerator implements IEquipmentController {

    @Autowired
    private BaseCore baseCore;

    @Override
    public Response equipmentReportDataCollectionReq(EquipmentReportDataCollectionReqParams reportDataCollectionReqParams) {
        String sql = "insert into omeqp_edc (id, link_key, edc_plan_id, edc_plan_rkey, edc_spec_id, edc_spec_rkey, refkey) values (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{"0", "0", "0", "0", "0", "0", "0"};
        baseCore.insert(sql, params);

        sql = "insert into omedcspec_item (id, link_key, refkey) values (?, ?, ?)";
        params = new Object[]{"0", "intdata", "0"};
        baseCore.insert(sql, params);

        return null;
    }
}
