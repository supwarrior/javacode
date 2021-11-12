package com.github.ddd.domainObject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubPersonDO is a Querydsl query type for SubPersonDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubPersonDO extends EntityPathBase<SubPersonDO> {

    private static final long serialVersionUID = 808450973L;

    public static final QSubPersonDO subPersonDO = new QSubPersonDO("subPersonDO");

    public final QChildEntity _super = new QChildEntity(this);

    public final StringPath companyID = createString("companyID");

    public final StringPath emailAddress = createString("emailAddress");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath password = createString("password");

    public final StringPath phoneNO = createString("phoneNO");

    //inherited
    public final StringPath referenceKey = _super.referenceKey;

    public final StringPath userID = createString("userID");

    public QSubPersonDO(String variable) {
        super(SubPersonDO.class, forVariable(variable));
    }

    public QSubPersonDO(Path<? extends SubPersonDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubPersonDO(PathMetadata metadata) {
        super(SubPersonDO.class, metadata);
    }

}

