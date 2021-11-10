package com.github.mycim.event;


import com.github.mycim.dto.CoreBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.List;

public class EdcEvent {

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class NonLotEdcEventRecord extends Event.EventRecord {
        private static final long serialVersionUID = 1745816381433686248L;
        private String joinObjectId;
        private String joinObjectRefKey;
        private String joinObjectType;
        private String edcPlanId;
        private String edcPlanRefKey;
        private String edcPlanDesc;
        private String edcSpecId;
        private String edcSpecRefKey;
        private String edcSpecDesc;
        private Timestamp reportTime;
        private String reportType;
        private List<NonLotEdcItemsData> edcItems;
        private List<NonLotEdcSpecsData> edcSpecs;
    }

    @Data
    public static class NonLotEdcItemsData implements CoreBaseDTO {
        private static final long serialVersionUID = -2693186907797599055L;

        private String edcItemName;
        private String edcMode;
        private String dataCollectionUnit;
        private String dataType;
        private String itemType;
        private String measType;
        private Boolean storeFlag;
        private String calcType;
        private String calcExpr;
        private String dataValue;
        private String targetValue;
        private String specCheckResult;
        private String actionCode;
    }

    @Data
    public static class NonLotEdcSpecsData implements CoreBaseDTO {
        private static final long serialVersionUID = -2693186907797599055L;

        private String edcItemName;
        private Boolean screenUpperRequired;
        private Double screenUpperLimit;
        private String screenUpperActions;
        private Boolean screenLowerRequired;
        private Double screenLowerLimit;
        private String screenLowerActions;
        private Boolean specUpperRequired;
        private Double specUpperLimit;
        private String specUpperActions;
        private Boolean specLowerRequired;
        private Double specLowerLimit;
        private String specLowerActions;
        private Boolean controlUpperRequired;
        private Double controlUpperLimit;
        private String controlUpperActions;
        private Boolean controlLowerRequired;
        private String controlLowerActions;
        private Double controlLowerLimit;
        private Double dataCollectionItemTarget;
        private String dataCollectionItemTag;
        private String dataCollectionSpecGroup;
    }


}
