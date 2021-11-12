package com.github.mycim.doCell;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimDataCollectionDefDO is a Querydsl query type for CimDataCollectionDefDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimDataCollectionDefDO extends EntityPathBase<CimDataCollectionDefDO> {

    private static final long serialVersionUID = -1271941231L;

    public static final QCimDataCollectionDefDO cimDataCollectionDefDO = new QCimDataCollectionDefDO("cimDataCollectionDefDO");

    public final com.github.ddd.domainObject.QMainEntity _super = new com.github.ddd.domainObject.QMainEntity(this);

    public final StringPath collectionUseType = createString("collectionUseType");

    public final StringPath dataCollectionDefID = createString("dataCollectionDefID");

    public final StringPath dcSettingType = createString("dcSettingType");

    public final StringPath dcType = createString("dcType");

    public final StringPath description = createString("description");

    public final StringPath fpcCategory = createString("fpcCategory");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath versionId = createString("versionId");

    public final BooleanPath whiteFlag = createBoolean("whiteFlag");

    public QCimDataCollectionDefDO(String variable) {
        super(CimDataCollectionDefDO.class, forVariable(variable));
    }

    public QCimDataCollectionDefDO(Path<? extends CimDataCollectionDefDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimDataCollectionDefDO(PathMetadata metadata) {
        super(CimDataCollectionDefDO.class, metadata);
    }

}

