package com.github.esec.bus;

import com.github.esec.entity.PersonPrivilegeGroupDO;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonPrivilegeGroupDO extends EntityPathBase<PersonPrivilegeGroupDO> {


    public QPersonPrivilegeGroupDO(String variable) {
        super(PersonPrivilegeGroupDO.class, forVariable(variable));
    }

    public static final QPersonPrivilegeGroupDO personPrivilegeGroupDO = new QPersonPrivilegeGroupDO("personPrivilegeGroupDO");

    public final StringPath dKey = createString("dKey");

    public final StringPath id = createString("id");

}
