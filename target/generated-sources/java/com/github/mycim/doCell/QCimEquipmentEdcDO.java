package com.github.mycim.doCell;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimEquipmentEdcDO is a Querydsl query type for CimEquipmentEdcDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimEquipmentEdcDO extends EntityPathBase<CimEquipmentEdcDO> {

    private static final long serialVersionUID = 1632942936L;

    public static final QCimEquipmentEdcDO cimEquipmentEdcDO = new QCimEquipmentEdcDO("cimEquipmentEdcDO");

    public final com.github.ddd.domainObject.QChildEntity _super = new com.github.ddd.domainObject.QChildEntity(this);

    public final StringPath edcPlanId = createString("edcPlanId");

    public final StringPath edcPlanReferenceKey = createString("edcPlanReferenceKey");

    public final StringPath edcSpecId = createString("edcSpecId");

    public final StringPath edcSpecReferenceKey = createString("edcSpecReferenceKey");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath linkKey = createString("linkKey");

    //inherited
    public final StringPath referenceKey = _super.referenceKey;

    public QCimEquipmentEdcDO(String variable) {
        super(CimEquipmentEdcDO.class, forVariable(variable));
    }

    public QCimEquipmentEdcDO(Path<? extends CimEquipmentEdcDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimEquipmentEdcDO(PathMetadata metadata) {
        super(CimEquipmentEdcDO.class, metadata);
    }

}

