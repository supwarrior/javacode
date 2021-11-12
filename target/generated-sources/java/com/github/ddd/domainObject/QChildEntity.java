package com.github.ddd.domainObject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChildEntity is a Querydsl query type for ChildEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QChildEntity extends EntityPathBase<ChildEntity> {

    private static final long serialVersionUID = 1656059580L;

    public static final QChildEntity childEntity = new QChildEntity("childEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath referenceKey = createString("referenceKey");

    public QChildEntity(String variable) {
        super(ChildEntity.class, forVariable(variable));
    }

    public QChildEntity(Path<? extends ChildEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChildEntity(PathMetadata metadata) {
        super(ChildEntity.class, metadata);
    }

}

