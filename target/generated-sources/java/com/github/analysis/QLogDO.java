package com.github.analysis;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogDO is a Querydsl query type for LogDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogDO extends EntityPathBase<LogDO> {

    private static final long serialVersionUID = 839541211L;

    public static final QLogDO logDO = new QLogDO("logDO");

    public final com.github.ddd.domainObject.QMainEntity _super = new com.github.ddd.domainObject.QMainEntity(this);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath methodName = createString("methodName");

    public final StringPath requestTime = createString("requestTime");

    public final StringPath time = createString("time");

    public final StringPath transactionId = createString("transactionId");

    public QLogDO(String variable) {
        super(LogDO.class, forVariable(variable));
    }

    public QLogDO(Path<? extends LogDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogDO(PathMetadata metadata) {
        super(LogDO.class, metadata);
    }

}

