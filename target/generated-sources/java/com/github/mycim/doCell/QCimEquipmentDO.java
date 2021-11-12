package com.github.mycim.doCell;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimEquipmentDO is a Querydsl query type for CimEquipmentDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimEquipmentDO extends EntityPathBase<CimEquipmentDO> {

    private static final long serialVersionUID = -94953246L;

    public static final QCimEquipmentDO cimEquipmentDO = new QCimEquipmentDO("cimEquipmentDO");

    public final com.github.ddd.domainObject.QMainEntity _super = new com.github.ddd.domainObject.QMainEntity(this);

    public final BooleanPath availableStateFlag = createBoolean("availableStateFlag");

    public final StringPath backupEquipmentStateID = createString("backupEquipmentStateID");

    public final StringPath backupEquipmentStateObj = createString("backupEquipmentStateObj");

    public final BooleanPath batchNotification = createBoolean("batchNotification");

    public final StringPath capableOperationMode = createString("capableOperationMode");

    public final BooleanPath cassetteChangeRequired = createBoolean("cassetteChangeRequired");

    public final BooleanPath cassetteReadableFlag = createBoolean("cassetteReadableFlag");

    public final DateTimePath<java.sql.Timestamp> claimTime = createDateTime("claimTime", java.sql.Timestamp.class);

    public final StringPath claimUserID = createString("claimUserID");

    public final StringPath claimUserObj = createString("claimUserObj");

    public final StringPath currentOperationMode = createString("currentOperationMode");

    public final StringPath currentStateID = createString("currentStateID");

    public final StringPath currentStateObj = createString("currentStateObj");

    public final StringPath dispatchID = createString("dispatchID");

    public final BooleanPath emptyCassetteEarlyOutFlag = createBoolean("emptyCassetteEarlyOutFlag");

    public final StringPath eqpModel = createString("eqpModel");

    public final StringPath equipmentCategory = createString("equipmentCategory");

    public final StringPath equipmentControl = createString("equipmentControl");

    public final StringPath equipmentID = createString("equipmentID");

    public final StringPath equipmentName = createString("equipmentName");

    public final DateTimePath<java.sql.Timestamp> equipmentStateChangeTime = createDateTime("equipmentStateChangeTime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> equipmentStateHistoryTime = createDateTime("equipmentStateHistoryTime", java.sql.Timestamp.class);

    public final BooleanPath equipmentToEquipmentTransfer = createBoolean("equipmentToEquipmentTransfer");

    public final StringPath equipmentType = createString("equipmentType");

    public final BooleanPath fixtureRequired = createBoolean("fixtureRequired");

    public final StringPath flowBatchID = createString("flowBatchID");

    public final NumberPath<Integer> flowBatchMaxCount = createNumber("flowBatchMaxCount", Integer.class);

    public final StringPath flowBatchObj = createString("flowBatchObj");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath lastControlJobCreateDate = createString("lastControlJobCreateDate");

    public final NumberPath<Integer> lastControlJobNumber = createNumber("lastControlJobNumber", Integer.class);

    public final StringPath lastEquipmentStateID = createString("lastEquipmentStateID");

    public final StringPath lastEquipmentStateObj = createString("lastEquipmentStateObj");

    public final DateTimePath<java.sql.Timestamp> maintenanceTime = createDateTime("maintenanceTime", java.sql.Timestamp.class);

    public final StringPath maintenanceUserID = createString("maintenanceUserID");

    public final StringPath maintenanceUserObj = createString("maintenanceUserObj");

    public final NumberPath<Integer> maxIntervalTime = createNumber("maxIntervalTime", Integer.class);

    public final NumberPath<Integer> maxOperationStartCount = createNumber("maxOperationStartCount", Integer.class);

    public final NumberPath<Integer> maxReticleCapacity = createNumber("maxReticleCapacity", Integer.class);

    public final NumberPath<Integer> maxRunTime = createNumber("maxRunTime", Integer.class);

    public final NumberPath<Integer> maxRunWaferCount = createNumber("maxRunWaferCount", Integer.class);

    public final NumberPath<Integer> minWaferCount = createNumber("minWaferCount", Integer.class);

    public final DateTimePath<java.sql.Timestamp> modeChangeTime = createDateTime("modeChangeTime", java.sql.Timestamp.class);

    public final StringPath modeChangeUserID = createString("modeChangeUserID");

    public final StringPath modeChangeUserObj = createString("modeChangeUserObj");

    public final BooleanPath monitorCreation = createBoolean("monitorCreation");

    public final StringPath multiRecipeCapability = createString("multiRecipeCapability");

    public final StringPath onlineMode = createString("onlineMode");

    public final NumberPath<Integer> operationStartCount = createNumber("operationStartCount", Integer.class);

    public final BooleanPath outInTransferFlag = createBoolean("outInTransferFlag");

    public final StringPath prControl = createString("prControl");

    public final BooleanPath processBatchAvailableFlag = createBoolean("processBatchAvailableFlag");

    public final BooleanPath processJobControlFlag = createBoolean("processJobControlFlag");

    public final StringPath propertyObj = createString("propertyObj");

    public final StringPath rawEquipmentStateID = createString("rawEquipmentStateID");

    public final StringPath rawEquipmentStateObj = createString("rawEquipmentStateObj");

    public final BooleanPath recipeBodyFlag = createBoolean("recipeBodyFlag");

    public final BooleanPath reticleRequired = createBoolean("reticleRequired");

    public final NumberPath<Integer> reticleStoreLimit = createNumber("reticleStoreLimit", Integer.class);

    public final NumberPath<Integer> runTime = createNumber("runTime", Integer.class);

    public final NumberPath<Integer> runWaferCount = createNumber("runWaferCount", Integer.class);

    public final StringPath samplePolicyName = createString("samplePolicyName");

    public final StringPath sampleWaferAttribute = createString("sampleWaferAttribute");

    public final StringPath serverName = createString("serverName");

    public final BooleanPath slmCapabilityFlag = createBoolean("slmCapabilityFlag");

    public final StringPath slmSwitch = createString("slmSwitch");

    public final NumberPath<Integer> standardWaferValue = createNumber("standardWaferValue", Integer.class);

    public final BooleanPath startLotNotifyRequired = createBoolean("startLotNotifyRequired");

    public final DateTimePath<java.sql.Timestamp> stateChangeTime = createDateTime("stateChangeTime", java.sql.Timestamp.class);

    public final StringPath stateChangeUserID = createString("stateChangeUserID");

    public final StringPath stateChangeUserObj = createString("stateChangeUserObj");

    public final StringPath subEquipmentID = createString("subEquipmentID");

    public final StringPath subEquipmentObj = createString("subEquipmentObj");

    public final StringPath usedRecipeID = createString("usedRecipeID");

    public final StringPath usedRecipeObj = createString("usedRecipeObj");

    public final BooleanPath utsPriorityFlag = createBoolean("utsPriorityFlag");

    public final BooleanPath waferIDReadableFlag = createBoolean("waferIDReadableFlag");

    public final BooleanPath waferMapCheckableFlag = createBoolean("waferMapCheckableFlag");

    public final BooleanPath whiteFlag = createBoolean("whiteFlag");

    public QCimEquipmentDO(String variable) {
        super(CimEquipmentDO.class, forVariable(variable));
    }

    public QCimEquipmentDO(Path<? extends CimEquipmentDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimEquipmentDO(PathMetadata metadata) {
        super(CimEquipmentDO.class, metadata);
    }

}

