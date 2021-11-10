package com.github.mycim.dto;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.github.mycim.doCell.CimDataCollectionSpecItemDO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class EDCDTO {

    @Data
    public static class DCItemData implements CoreBaseDTO {
        private static final long serialVersionUID = 6237486246150545672L;
        private String dataItemName;
        private String waferPosition;
        private String sitePosition;
        private String valType;
        private String itemType;
        private String calculationType;
        private String calculationExpression;
        private String inputValue;
        private Double numValue;
        private String specCheckResult;
        private List<String> actionCodes;
        private String waferID;
        private String measurementType;

        // 3片9点添加字段
        private Integer waferCount;
        private Integer siteCount;
        private int seqNo;

        // EDC 性能优化添加字段
        private String targetValue;

        public DCItemData copy() {
            DCItemData retVal = new DCItemData();
            retVal.setDataItemName(this.dataItemName);
            retVal.setWaferPosition(this.waferPosition);
            retVal.setSitePosition(this.sitePosition);
            retVal.setValType(this.valType);
            retVal.setItemType(this.itemType);
            retVal.setCalculationType(this.calculationType);
            retVal.setCalculationExpression(this.calculationExpression);
            retVal.setInputValue(this.inputValue);
            retVal.setNumValue(this.numValue);
            retVal.setSpecCheckResult(this.specCheckResult);
            if (CollectionUtils.isNotEmpty(this.actionCodes)) {
                retVal.setActionCodes(new ArrayList<>(this.actionCodes));
            }
            retVal.setWaferID(this.waferID);
            retVal.setMeasurementType(this.measurementType);
            retVal.setWaferCount(this.getWaferCount());
            retVal.setSiteCount(this.getSiteCount());
            retVal.setSeqNo(this.seqNo);
            retVal.setTargetValue(this.targetValue);
            return retVal;
        }
    }

    @Data
    public static class DCItemSpecification implements CoreBaseDTO {
        private static final long serialVersionUID = 790203371269953799L;
        private String dataItemName;
        private Boolean screenLimitUpperRequired;
        private Double screenLimitUpper;
        private String actionCodesUscrn;
        private Boolean screenLimitLowerRequired;
        private Double screenLimitLower;
        private String actionCodesLscrn;
        private Boolean specLimitUpperRequired;
        private Double specLimitUpper;
        private String actionCodesUsl;
        private Boolean specLimitLowerRequired;
        private Double specLimitLower;
        private String actionCodesLsl;
        private Boolean controlLimitUpperRequired;
        private Double controlLimitUpper;
        private String actionCodesUcl;
        private Boolean controlLimitLowerRequired;
        private Double controlLimitLower;
        private String actionCodesLcl;
        private Double target;
        private String tag;
        private String dcSpecGroup;
    }

    @Data
    public static class NonLotEdcData implements CoreBaseDTO {
        private static final long serialVersionUID = 5416172822825046620L;
        private String edcPlanId;
        private String edcPlanRKey;
        private String edcSpecId;
        private String edcSpecRKey;
    }

    public static EDCDTO.DCItemSpecification getSpecItemInfo(CimDataCollectionSpecItemDO cimDataCollectionSpecItemDO) {
        EDCDTO.DCItemSpecification dcItemSpecification = new DCItemSpecification();
        dcItemSpecification.setDataItemName(cimDataCollectionSpecItemDO.getDataCollectionItemName());
        dcItemSpecification.setScreenLimitUpperRequired(cimDataCollectionSpecItemDO.getScreenUpperRequired());
        dcItemSpecification.setScreenLimitUpper(cimDataCollectionSpecItemDO.getScreenUpperLimit());
        dcItemSpecification.setActionCodesUscrn(cimDataCollectionSpecItemDO.getScreenUpperActions());
        dcItemSpecification.setActionCodesLscrn(cimDataCollectionSpecItemDO.getScreenLowerActions());
        dcItemSpecification.setScreenLimitLowerRequired(cimDataCollectionSpecItemDO.getScreenLowerRequired());
        dcItemSpecification.setScreenLimitLower(cimDataCollectionSpecItemDO.getScreenLowerLimit());
        dcItemSpecification.setSpecLimitUpperRequired(cimDataCollectionSpecItemDO.getSpecUpperRequired());
        dcItemSpecification.setSpecLimitUpper(cimDataCollectionSpecItemDO.getSpecUpperLimit());
        dcItemSpecification.setActionCodesUsl(cimDataCollectionSpecItemDO.getSpecUpperActions());
        dcItemSpecification.setActionCodesLsl(cimDataCollectionSpecItemDO.getSpecLowerActions());
        dcItemSpecification.setSpecLimitLowerRequired(cimDataCollectionSpecItemDO.getSpecLowerRequired());
        dcItemSpecification.setSpecLimitLower(cimDataCollectionSpecItemDO.getSpecLowerLimit());
        dcItemSpecification.setControlLimitUpperRequired(cimDataCollectionSpecItemDO.getControlUpperRequired());
        dcItemSpecification.setControlLimitUpper(cimDataCollectionSpecItemDO.getControlUpperLimit());
        dcItemSpecification.setActionCodesUcl(cimDataCollectionSpecItemDO.getControlUpperActions());
        dcItemSpecification.setActionCodesLcl(cimDataCollectionSpecItemDO.getControlLowerActions());
        dcItemSpecification.setControlLimitLowerRequired(cimDataCollectionSpecItemDO.getControlLowerRequired());
        dcItemSpecification.setControlLimitLower(cimDataCollectionSpecItemDO.getControlLowerLimit());
        dcItemSpecification.setTarget(cimDataCollectionSpecItemDO.getDataCollectionItemTarget());
        dcItemSpecification.setTag(cimDataCollectionSpecItemDO.getDataCollectionItemTag());
        dcItemSpecification.setDcSpecGroup(cimDataCollectionSpecItemDO.getDataCollectionSpecGroup());
        return dcItemSpecification;
    }
}
