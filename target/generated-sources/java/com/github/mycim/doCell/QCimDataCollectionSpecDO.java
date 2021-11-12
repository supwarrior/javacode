package com.github.mycim.doCell;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimDataCollectionSpecDO is a Querydsl query type for CimDataCollectionSpecDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimDataCollectionSpecDO extends EntityPathBase<CimDataCollectionSpecDO> {

    private static final long serialVersionUID = -335876763L;

    public static final QCimDataCollectionSpecDO cimDataCollectionSpecDO = new QCimDataCollectionSpecDO("cimDataCollectionSpecDO");

    public final com.github.ddd.domainObject.QMainEntity _super = new com.github.ddd.domainObject.QMainEntity(this);

    public final StringPath collectionUseType = createString("collectionUseType");

    public final StringPath dataCollectionDefID = createString("dataCollectionDefID");

    public final StringPath dataCollectionDefObj = createString("dataCollectionDefObj");

    public final StringPath dataCollectionSpecID = createString("dataCollectionSpecID");

    public final StringPath description = createString("description");

    public final StringPath fpcategory = createString("fpcategory");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath versionID = createString("versionID");

    public final BooleanPath whitelag = createBoolean("whitelag");

    public QCimDataCollectionSpecDO(String variable) {
        super(CimDataCollectionSpecDO.class, forVariable(variable));
    }

    public QCimDataCollectionSpecDO(Path<? extends CimDataCollectionSpecDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimDataCollectionSpecDO(PathMetadata metadata) {
        super(CimDataCollectionSpecDO.class, metadata);
    }

}

