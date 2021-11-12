package com.github.esec.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonPrivilegeGroupDO is a Querydsl query type for PersonPrivilegeGroupDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonPrivilegeGroupDO extends EntityPathBase<PersonPrivilegeGroupDO> {

    private static final long serialVersionUID = -1952719755L;

    public static final QPersonPrivilegeGroupDO personPrivilegeGroupDO = new QPersonPrivilegeGroupDO("personPrivilegeGroupDO");

    public final com.github.esec.core.QBaseEntity _super = new com.github.esec.core.QBaseEntity(this);

    public final StringPath dKey = createString("dKey");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath privilegeGroupKey = createString("privilegeGroupKey");

    public final StringPath privilegeGroupReferenceKey = createString("privilegeGroupReferenceKey");

    public final StringPath referenceKey = createString("referenceKey");

    public QPersonPrivilegeGroupDO(String variable) {
        super(PersonPrivilegeGroupDO.class, forVariable(variable));
    }

    public QPersonPrivilegeGroupDO(Path<? extends PersonPrivilegeGroupDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonPrivilegeGroupDO(PathMetadata metadata) {
        super(PersonPrivilegeGroupDO.class, metadata);
    }

}

