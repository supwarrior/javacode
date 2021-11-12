package com.github.mycim.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventFIFOBaseDO is a Querydsl query type for EventFIFOBaseDO
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QEventFIFOBaseDO extends EntityPathBase<EventFIFOBaseDO> {

    private static final long serialVersionUID = 1551604637L;

    public static final QEventFIFOBaseDO eventFIFOBaseDO = new QEventFIFOBaseDO("eventFIFOBaseDO");

    public final com.github.ddd.domainObject.QChildEntity _super = new com.github.ddd.domainObject.QChildEntity(this);

    public final StringPath dKey = createString("dKey");

    public final StringPath eventObj = createString("eventObj");

    public final StringPath eventTime = createString("eventTime");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath referenceKey = _super.referenceKey;

    public final StringPath watchdogName = createString("watchdogName");

    public QEventFIFOBaseDO(String variable) {
        super(EventFIFOBaseDO.class, forVariable(variable));
    }

    public QEventFIFOBaseDO(Path<? extends EventFIFOBaseDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventFIFOBaseDO(PathMetadata metadata) {
        super(EventFIFOBaseDO.class, metadata);
    }

}

