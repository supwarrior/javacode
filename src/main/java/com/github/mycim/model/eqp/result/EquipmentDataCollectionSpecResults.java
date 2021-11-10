package com.github.mycim.model.eqp.result;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.dto.EDCDTO;
import lombok.Data;

import java.util.List;

@Data
public class EquipmentDataCollectionSpecResults {

    /**
     * data collection的items配置
     */
    private List<EDCDTO.DCItemData> dataCollectionItems;

    /**
     * spec list
     */
    List<EDCDTO.DCItemSpecification> specificationList;

    /**
     * 是否执行spec action
     */
    private boolean isExecuteSpecAction;

    /**
     * EDC ID
     */
    private ObjectIdentifier dataCollectionId;
    private String dataCollectionDesc;

    /**
     * SPEC ID
     */
    private ObjectIdentifier dataCollectionSpecId;
    private String dataCollectionSpecDesc;
}
