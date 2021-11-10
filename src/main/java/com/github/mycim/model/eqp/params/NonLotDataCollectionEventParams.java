package com.github.mycim.model.eqp.params;

import com.github.mycim.event.EdcEvent;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class NonLotDataCollectionEventParams {

    /**
     * join = equipment / carrier / reticle pod
     */
    private String joinObjectId;
    private String joinObjectRefKey;
    private String joinObjectType;
    /**
     * edc
     */
    private String edcPlanId;
    private String edcPlanRefKey;
    private String edcPlanDesc;

    /**
     * spec
     */
    private String edcSpecId;
    private String edcSpecRefKey;
    private String edcSpecDesc;

    /**
     * report time / type (auto / manual)
     */
    private Timestamp reportTime;
    private String reportType;

    /**
     * items / specs
     */
    private List<EdcEvent.NonLotEdcItemsData> edcItems;
    private List<EdcEvent.NonLotEdcSpecsData> edcSpecs;
}
