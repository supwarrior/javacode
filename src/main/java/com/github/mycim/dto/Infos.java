package com.github.mycim.dto;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.common.support.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

public class Infos {

    @Data
    public static class EqpChamberStatus {
        private ObjectIdentifier chamberID;
        private ObjectIdentifier chamberStatusCode;
    }


    @Data
    public static class ObjCommon {
        private String transactionID;
        private User user;
        private TimeStamp timeStamp;
        private Object reserve;           //<i>Reserved for myCIM4.0 customization

        public ObjCommon duplicate() {
            ObjCommon retVal = new ObjCommon();
            retVal.transactionID = transactionID;
            retVal.user = user.duplicate();
            retVal.timeStamp = timeStamp.duplicate();
            retVal.reserve = reserve;
            return retVal;
        }
    }

    @Data
    public static class TimeStamp {
        private Timestamp reportTimeStamp = new Timestamp(System.currentTimeMillis());
        private Double reportShopDate;
        private Object reserve;

        public TimeStamp duplicate() {
            TimeStamp timeStamp = new TimeStamp();
            timeStamp.reportTimeStamp = new Timestamp(timeStamp.reportTimeStamp.getTime());
            timeStamp.reportShopDate = reportShopDate;
            timeStamp.reserve = reserve;
            return timeStamp;
        }
    }

    @Data
    public static class DataCollectionItemInfo {
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

        public ProcessDTO.DataCollectionItemInfo convert() {
            ProcessDTO.DataCollectionItemInfo itemInfo = new ProcessDTO.DataCollectionItemInfo();
            itemInfo.setDataCollectionItemName(this.getDataCollectionItemName());
            itemInfo.setDataCollectionMode(this.getDataCollectionMode());
            itemInfo.setDataCollectionUnit(this.getDataCollectionUnit());
            itemInfo.setDataType(this.getDataType());
            itemInfo.setItemType(this.getItemType());
            itemInfo.setMeasurementType(this.getMeasurementType());
            itemInfo.setWaferID(this.getWaferID());
            itemInfo.setWaferPosition(this.getWaferPosition());
            itemInfo.setSitePosition(this.getSitePosition());
            itemInfo.setHistoryRequiredFlag(this.getHistoryRequiredFlag());
            itemInfo.setCalculationType(this.getCalculationType());
            itemInfo.setCalculationExpression(this.getCalculationExpression());
            itemInfo.setDataValue(this.getDataValue());
            itemInfo.setTargetValue(this.getTargetValue());
            itemInfo.setSpecCheckResult(this.getSpecCheckResult());
            itemInfo.setActionCodes(this.getActionCodes());
            itemInfo.setSeqNo(this.seqNo);
            itemInfo.setWaferCount(this.waferCount);
            itemInfo.setSiteCount(this.siteCount);
            return itemInfo;
        }
    }

    @Data
    public static class ConstraintDetailAttributes {
        private List<EntityIdentifier> entities;
        private List<String> subLotTypes;
        private String startTimeStamp;
        private String endTimeStamp;
        private String constraintType;
        private String memo;
        private String reasonCode;
        private String status;
        private ObjectIdentifier ownerID;
        private String claimedTimeStamp;
        private List<Infos.EntityIdentifier> exceptionEntities;
        private List<Infos.EntityInhibitExceptionLotInfo> exceptionLotList;
        private String functionRule;
        private boolean specTool;
    }

    @Data
    public static class EntityInhibitExceptionLotInfo {
        // Entity Inhibit Exception lot
        private ObjectIdentifier lotID;
        // 0:Multi  1:Single
        private Boolean singleTriggerFlag;
        // 0:NotUse 1:Used
        private Boolean usedFlag;
        // Owner ID of who registered the Inhibit Exception.
        private ObjectIdentifier claimUserID;
        // Comment for Inhibit Exception
        private String claimMemo;
        // Claimed Time Stamp
        private String claimTime;
    }

    @Data
    public static class EntityIdentifier {
        private String className;
        private ObjectIdentifier objectID;
        private String attribution;
        private String siInfo;
        public EntityIdentifier() {
        }

        public EntityIdentifier(String className, ObjectIdentifier objectID) {
            this.className = className;
            this.objectID = objectID;
        }

        public EntityIdentifier(String className, ObjectIdentifier objectID, String attribution) {
            this.className = className;
            this.objectID = objectID;
            this.attribution = attribution;
        }
    }

    @Data
    public static class ConstraintEqpDetailInfo {
        private ObjectIdentifier entityInhibitID;
        private ConstraintDetailAttributes entityInhibitDetailAttributes;
    }
}
