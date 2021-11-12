package com.github.ddd.domainObject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonDO is a Querydsl query type for PersonDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonDO extends EntityPathBase<PersonDO> {

    private static final long serialVersionUID = -1524500189L;

    public static final QPersonDO personDO = new QPersonDO("personDO");

    public final QMainEntity _super = new QMainEntity(this);

    public final StringPath companyID = createString("companyID");

    public final StringPath emailAddress = createString("emailAddress");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath password = createString("password");

    public final StringPath phoneNO = createString("phoneNO");

    public final StringPath userID = createString("userID");

    public QPersonDO(String variable) {
        super(PersonDO.class, forVariable(variable));
    }

    public QPersonDO(Path<? extends PersonDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonDO(PathMetadata metadata) {
        super(PersonDO.class, metadata);
    }

}

