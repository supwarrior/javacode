package com.github.mycim.doCell;

import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import com.github.ddd.domainObject.MainEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@IdPrefix("OMEQP")
@Table(
        name = "OMEQP"
)
@Data
@MasterEntity
public class CimEquipmentDO extends MainEntity {

    @Column(
            name = "EQP_ID",
            length = 64,
            unique = true
    )
    @NamedIdentifier
    private String equipmentID;
    @Column(
            name = "EQP_NAME",
            length = 64
    )
    private String equipmentName;
    @Column(
            name = "EQP_STATE_ID",
            length = 64
    )
    private String currentStateID;
    @Column(
            name = "EQP_STATE_RKEY",
            length = 64
    )
    private String currentStateObj;
    @Column(
            name = "ACTUAL_STATE_ID",
            length = 64
    )
    private String lastEquipmentStateID;
    @Column(
            name = "ACTUAL_STATE_RKEY",
            length = 64
    )
    private String lastEquipmentStateObj;
    @Column(
            name = "EQP_CATEGORY",
            length = 20
    )
    private String equipmentCategory;
    @Column(
            name = "EQP_GRP_ID",
            length = 64
    )
    private String equipmentType;
    @Column(
            name = "EAP_HOST",
            length = 64
    )
    private String serverName;

    @Column(
            name = "SUPPORTED_OPE_MODE",
            length = 20
    )
    private String capableOperationMode;

    @Column(
            name = "CUR_OPE_MODE",
            length = 20
    )
    private String currentOperationMode;

    @Column(
            name = "ONLINE_MODE",
            length = 20
    )
    private String onlineMode;
    @Column(
            name = "SPECIAL_EQP_FUNC",
            length = 64
    )
    private String equipmentControl;

    @Column(
            name = "E2E_XFER_FLAG"
    )
    private Boolean equipmentToEquipmentTransfer;

    @Column(
            name = "RTCL_NEED_FLAG"
    )
    private Boolean reticleRequired;

    @Column(
            name = "FIXT_NEED_FLAG"
    )
    private Boolean fixtureRequired;

    @Column(
            name = "MOVE_IN_RSV_REQD"
    )
    private Boolean startLotNotifyRequired;

    @Column(
            name = "CARRIER_CHG_FLAG"
    )
    private Boolean cassetteChangeRequired;

    @Column(
            name = "TOTI_XFER_FLAG"
    )
    private Boolean outInTransferFlag;
    @Column(
            name = "AVAILABLE_FLAG"
    )
    private Boolean availableStateFlag;

    @Column(
            name = "RCP_BODY_FLAG"
    )
    private Boolean recipeBodyFlag;
    @Column(
            name = "DISP_RKEY",
            length = 64
    )
    private String dispatchID;
    @Column(
            name = "FLOWB_ID",
            length = 128
    )
    private String flowBatchID;
    @Column(
            name = "FLOWB_RKEY",
            length = 64
    )
    private String flowBatchObj;

    @Column(
            name = "MAX_RUN_WAFER_COUNT"
    )
    private Integer maxRunWaferCount;

    @Column(
            name = "RUN_WAFER_COUNT"
    )
    private Integer runWaferCount;

    @Column(
            name = "MAX_MOVE_IN_COUNT"
    )
    private Integer maxOperationStartCount;

    @Column(
            name = "MOVE_IN_COUNT"
    )
    private Integer operationStartCount;

    @Column(
            name = "MAX_RUN_TIME"
    )
    private Integer maxRunTime;

    @Column(
            name = "RUN_TIME"
    )
    private Integer runTime;

    @Column(
            name = "PM_INTERVAL"
    )
    private Integer maxIntervalTime;
    @Column(
            name = "LAST_USED_RECIPE_ID",
            length = 64
    )
    private String usedRecipeID;
    @Column(
            name = "LAST_USED_RECIPE_RKEY",
            length = 135
    )
    private String usedRecipeObj;
    @Column(
            name = "LAST_TRX_TIME"
    )

    private Timestamp claimTime;
    @Column(
            name = "LAST_TRX_USER_ID",
            length = 64
    )
    private String claimUserID;
    @Column(
            name = "LAST_TRX_USER_RKEY",
            length = 64
    )
    private String claimUserObj;
    @Column(
            name = "LAST_MAINT_TIME"
    )
    private Timestamp maintenanceTime;
    @Column(
            name = "LAST_MAINT_USER_ID",
            length = 64
    )
    private String maintenanceUserID;
    @Column(
            name = "LAST_MAINT_USER_RKEY",
            length = 64
    )
    private String maintenanceUserObj;
    @Column(
            name = "LAST_STATE_CHG_TIME"
    )
    private Timestamp stateChangeTime;
    @Column(
            name = "LAST_STATE_CHG_USER_ID",
            length = 64
    )
    private String stateChangeUserID;
    @Column(
            name = "LAST_STATE_CHG_USER_RKEY",
            length = 64
    )
    private String stateChangeUserObj;
    @Column(
            name = "ACTUAL_STATE_CHG_TIME"
    )
    private Timestamp equipmentStateChangeTime;
    @Column(
            name = "STATE_HIST_CREATE_TIME"
    )
    private Timestamp equipmentStateHistoryTime;

    @Column(
            name = "MON_LOT_CREATE_FLAG"
    )
    private Boolean monitorCreation;
    @Column(
            name = "LAST_MODE_CHG_USER_ID",
            length = 64
    )
    private String modeChangeUserID;
    @Column(
            name = "LAST_MODE_CHG_USER_RKEY",
            length = 64
    )
    private String modeChangeUserObj;
    @Column(
            name = "LAST_MODE_CHG_TIME"
    )
    private Timestamp modeChangeTime;
    @Column(
            name = "PHY_EQP_STATE_ID",
            length = 64
    )
    private String rawEquipmentStateID;
    @Column(
            name = "PHY_EQP_STATE_RKEY",
            length = 64
    )
    private String rawEquipmentStateObj;

    @Column(
            name = "STD_WPH"
    )
    private Integer standardWaferValue;
    @Column(
            name = "MULTI_RECIPE_CAPABLE",
            length = 32
    )
    private String multiRecipeCapability;

    @Column(
            name = "MIN_WAFER_COUNT"
    )
    private Integer minWaferCount;

    @Column(
            name = "NOTIFY_BATCH_IN_FLAG"
    )
    private Boolean batchNotification;

    @Column(
            name = "CARRIER_READ_FLAG"
    )
    private Boolean cassetteReadableFlag;

    @Column(
            name = "WAFER_MAP_CHECK_FLAG"
    )
    private Boolean waferMapCheckableFlag;

    @Column(
            name = "WAFER_ID_READ_FLAG"
    )
    private Boolean waferIDReadableFlag;

    @Column(
            name = "PROCESS_BATCH_FLAG"
    )
    private Boolean processBatchAvailableFlag;
    @Column(
            name = "FMC_CAPABILITY"
    )
    private Boolean slmCapabilityFlag;
    @Column(
            name = "FMC_SWITCH",
            length = 32
    )
    private String slmSwitch;

    @Column(
            name = "LAST_CJ_NO"
    )
    private Integer lastControlJobNumber;
    @Column(
            name = "LAST_CJ_DATE",
            length = 30
    )
    private String lastControlJobCreateDate;

    @Column(
            name = "EARLY_EMPTY_CARRIER_OUT"
    )
    private Boolean emptyCassetteEarlyOutFlag;
    @Column(
            name = "SUB_EQP_ID",
            length = 64
    )
    private String subEquipmentID;
    @Column(
            name = "SUB_EQP_RKEY",
            length = 64
    )
    private String subEquipmentObj;

    @Column(
            name = "MAX_RTCL_CAPACITY"
    )
    private Integer maxReticleCapacity;
    @Column(
            name = "MAX_RTCL_STORE"
    )
    private Integer reticleStoreLimit;

    @Column(
            name = "OHB_PRIORITY_FLAG"
    )
    private Boolean utsPriorityFlag;

    @Column(
            name = "TEMP_MODE_FLAG"
    )
    private Boolean whiteFlag;
    @Column(
            name = "WAFER_SAMPLE_RULE",
            length = 64
    )
    private String samplePolicyName;
    @Column(
            name = "WAFER_SAMPLE_ATTR",
            length = 128
    )
    private String sampleWaferAttribute;
    @Column(
            name = "MAX_FLOWB_COUNT"
    )
    private Integer flowBatchMaxCount;
    @Column(
            name = "SAVED_EQP_STATE_ID",
            length = 64
    )
    private String backupEquipmentStateID;
    @Column(
            name = "SAVED_EQP_STATE_RKEY",
            length = 64
    )
    private String backupEquipmentStateObj;
    @Column(
            name = "PJ_CTRL_FLAG"
    )
    private Boolean processJobControlFlag;
    @Column(
            name = "PROPERTY_RKEY",
            length = 64
    )
    private String propertyObj;
    @Column(
            name = "PR_CONTROL",
            length = 64
    )
    private String prControl;
    @Column(
            name = "EQP_MODEL",
            length = 30
    )
    private String eqpModel;
}
