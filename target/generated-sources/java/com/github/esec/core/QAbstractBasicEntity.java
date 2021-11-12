package com.github.esec.core;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractBasicEntity is a Querydsl query type for AbstractBasicEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QAbstractBasicEntity extends EntityPathBase<AbstractBasicEntity> {

    private static final long serialVersionUID = -109753180L;

    public static final QAbstractBasicEntity abstractBasicEntity = new QAbstractBasicEntity("abstractBasicEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath id = _super.id;

    public final DateTimePath<java.sql.Timestamp> lastClaimedTimeStamp = createDateTime("lastClaimedTimeStamp", java.sql.Timestamp.class);

    public final StringPath lastClaimedUserID = createString("lastClaimedUserID");

    public final StringPath lastClaimedUserObj = createString("lastClaimedUserObj");

    public QAbstractBasicEntity(String variable) {
        super(AbstractBasicEntity.class, forVariable(variable));
    }

    public QAbstractBasicEntity(Path<? extends AbstractBasicEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractBasicEntity(PathMetadata metadata) {
        super(AbstractBasicEntity.class, metadata);
    }

}

