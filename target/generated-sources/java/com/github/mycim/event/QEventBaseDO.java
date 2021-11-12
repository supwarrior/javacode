package com.github.mycim.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventBaseDO is a Querydsl query type for EventBaseDO
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QEventBaseDO extends EntityPathBase<EventBaseDO> {

    private static final long serialVersionUID = 1257507409L;

    public static final QEventBaseDO eventBaseDO = new QEventBaseDO("eventBaseDO");

    public final com.github.ddd.domainObject.QMainEntity _super = new com.github.ddd.domainObject.QMainEntity(this);

    public final StringPath claimMemo = createString("claimMemo");

    public final StringPath claimUserId = createString("claimUserId");

    public final StringPath eventCreateTime = createString("eventCreateTime");

    public final StringPath eventTime = createString("eventTime");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath objectManager = createString("objectManager");

    public final StringPath txId = createString("txId");

    public QEventBaseDO(String variable) {
        super(EventBaseDO.class, forVariable(variable));
    }

    public QEventBaseDO(Path<? extends EventBaseDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventBaseDO(PathMetadata metadata) {
        super(EventBaseDO.class, metadata);
    }

}

