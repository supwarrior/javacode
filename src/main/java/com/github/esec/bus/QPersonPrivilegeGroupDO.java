package com.github.esec.bus;

import com.github.esec.entity.PersonPrivilegeGroupDO;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonPrivilegeGroupDO extends EntityPathBase<PersonPrivilegeGroupDO> {

    private static final long serialVersionUID = -1952719755L;

    public static final com.github.esec.entity.QPersonPrivilegeGroupDO personPrivilegeGroupDO = new com.github.esec.entity.QPersonPrivilegeGroupDO("personPrivilegeGroupDO");

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
