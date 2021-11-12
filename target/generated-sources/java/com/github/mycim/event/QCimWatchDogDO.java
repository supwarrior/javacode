package com.github.mycim.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimWatchDogDO is a Querydsl query type for CimWatchDogDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimWatchDogDO extends EntityPathBase<CimWatchDogDO> {

    private static final long serialVersionUID = 1875658906L;

    public static final QCimWatchDogDO cimWatchDogDO = new QCimWatchDogDO("cimWatchDogDO");

    public final com.github.ddd.domainObject.QBaseEntity _super = new com.github.ddd.domainObject.QBaseEntity(this);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath transactionID = createString("transactionID");

    public final StringPath watchDogName = createString("watchDogName");

    public QCimWatchDogDO(String variable) {
        super(CimWatchDogDO.class, forVariable(variable));
    }

    public QCimWatchDogDO(Path<? extends CimWatchDogDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimWatchDogDO(PathMetadata metadata) {
        super(CimWatchDogDO.class, metadata);
    }

}

