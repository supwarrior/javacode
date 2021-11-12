package com.github.jpa.lock;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLockDO is a Querydsl query type for LockDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLockDO extends EntityPathBase<LockDO> {

    private static final long serialVersionUID = 88474280L;

    public static final QLockDO lockDO = new QLockDO("lockDO");

    public final com.github.ddd.domainObject.QMainEntity _super = new com.github.ddd.domainObject.QMainEntity(this);

    public final StringPath attributeKey = createString("attributeKey");

    //inherited
    public final StringPath id = _super.id;

    public final BooleanPath inUseFlag = createBoolean("inUseFlag");

    public final DateTimePath<java.sql.Timestamp> lastLockedTime = createDateTime("lastLockedTime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lastReleaseTime = createDateTime("lastReleaseTime", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> lockCreateTime = createDateTime("lockCreateTime", java.sql.Timestamp.class);

    public final StringPath objectKey = createString("objectKey");

    public QLockDO(String variable) {
        super(LockDO.class, forVariable(variable));
    }

    public QLockDO(Path<? extends LockDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLockDO(PathMetadata metadata) {
        super(LockDO.class, metadata);
    }

}

