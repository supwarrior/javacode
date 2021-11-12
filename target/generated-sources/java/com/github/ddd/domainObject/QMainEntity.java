package com.github.ddd.domainObject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMainEntity is a Querydsl query type for MainEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QMainEntity extends EntityPathBase<MainEntity> {

    private static final long serialVersionUID = -1080350529L;

    public static final QMainEntity mainEntity = new QMainEntity("mainEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath id = _super.id;

    public QMainEntity(String variable) {
        super(MainEntity.class, forVariable(variable));
    }

    public QMainEntity(Path<? extends MainEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMainEntity(PathMetadata metadata) {
        super(MainEntity.class, metadata);
    }

}

