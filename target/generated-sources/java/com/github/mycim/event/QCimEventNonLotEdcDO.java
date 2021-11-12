package com.github.mycim.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimEventNonLotEdcDO is a Querydsl query type for CimEventNonLotEdcDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimEventNonLotEdcDO extends EntityPathBase<CimEventNonLotEdcDO> {

    private static final long serialVersionUID = 1630962995L;

    public static final QCimEventNonLotEdcDO cimEventNonLotEdcDO = new QCimEventNonLotEdcDO("cimEventNonLotEdcDO");

    public final QEventBaseDO _super = new QEventBaseDO(this);

    //inherited
    public final StringPath claimMemo = _super.claimMemo;

    //inherited
    public final StringPath claimUserId = _super.claimUserId;

    public final StringPath edcPlanDesc = createString("edcPlanDesc");

    public final StringPath edcPlanId = createString("edcPlanId");

    public final StringPath edcPlanRefKey = createString("edcPlanRefKey");

    public final StringPath edcSpecDesc = createString("edcSpecDesc");

    public final StringPath edcSpecId = createString("edcSpecId");

    public final StringPath edcSpecRefKey = createString("edcSpecRefKey");

    //inherited
    public final StringPath eventCreateTime = _super.eventCreateTime;

    //inherited
    public final StringPath eventTime = _super.eventTime;

    //inherited
    public final StringPath id = _super.id;

    public final StringPath joinObjectId = createString("joinObjectId");

    public final StringPath joinObjectRefKey = createString("joinObjectRefKey");

    public final StringPath joinObjectType = createString("joinObjectType");

    //inherited
    public final StringPath objectManager = _super.objectManager;

    public final DateTimePath<java.sql.Timestamp> reportTime = createDateTime("reportTime", java.sql.Timestamp.class);

    public final StringPath reportType = createString("reportType");

    //inherited
    public final StringPath txId = _super.txId;

    public QCimEventNonLotEdcDO(String variable) {
        super(CimEventNonLotEdcDO.class, forVariable(variable));
    }

    public QCimEventNonLotEdcDO(Path<? extends CimEventNonLotEdcDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimEventNonLotEdcDO(PathMetadata metadata) {
        super(CimEventNonLotEdcDO.class, metadata);
    }

}

