package com.github.mycim.model.eqp.params;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.common.support.User;
import com.github.mycim.dto.Infos;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class EquipmentReportDataCollectionReqParams {

    /**
     * user
     */
    private User user;

    /**
     * 设备ID
     */
    private ObjectIdentifier equipmentId;

    /**
     * EDC Plan ID
     */
    private ObjectIdentifier dataCollectionPlanId;

    /**
     * dc items
     */
    private List<Infos.DataCollectionItemInfo> dataCollectionItemList;

    /**
     * 上报类型
     */
    private String reportType = "Manual";

    private Timestamp timestamp;

}
