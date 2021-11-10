package com.github.mycim.dto;

import com.github.jpa.lock.ObjectIdentifier;
import lombok.Data;

public class ProcessDTO {

    @Data
    public static class DataCollectionItemInfo implements CoreBaseDTO {
        private static final long serialVersionUID = 11134162571866482L;
        private String dataCollectionItemName;
        private String dataCollectionMode;
        private String dataCollectionUnit;
        private String dataType;
        private String itemType;
        private String measurementType;
        private ObjectIdentifier waferID;
        private String waferPosition;
        private String sitePosition;
        private Boolean historyRequiredFlag;
        private String calculationType;
        private String calculationExpression;
        private String dataValue;
        private String targetValue;
        private String specCheckResult;
        private String actionCodes;
        private int seqNo;
        private Integer waferCount;
        private Integer siteCount;
    }
}
