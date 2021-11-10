package com.github.mycim.model.eqp.result;

import com.github.jpa.lock.ObjectIdentifier;
import lombok.Data;

@Data
public class EquipmentDataCollectionReportResults {

    /**
     * item name
     */
    private String itemName;

    /**
     * EDC Plan
     */
    private ObjectIdentifier dataCollectionPlanId;

    /**
     * data Value
     */
    private String dataValue;

    /**
     * 目标值
     */
    private String targetValue;

    /**
     * spec action result
     */
    private String specActionResult;

    /**
     * action code;
     */
    private String actionCode;

}
