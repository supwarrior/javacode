package com.github.mycim.doCell;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCimDataCollectionSpecItemDO is a Querydsl query type for CimDataCollectionSpecItemDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCimDataCollectionSpecItemDO extends EntityPathBase<CimDataCollectionSpecItemDO> {

    private static final long serialVersionUID = 2064441048L;

    public static final QCimDataCollectionSpecItemDO cimDataCollectionSpecItemDO = new QCimDataCollectionSpecItemDO("cimDataCollectionSpecItemDO");

    public final com.github.ddd.domainObject.QChildEntity _super = new com.github.ddd.domainObject.QChildEntity(this);

    public final StringPath controlLowerActions = createString("controlLowerActions");

    public final NumberPath<Double> controlLowerLimit = createNumber("controlLowerLimit", Double.class);

    public final BooleanPath controlLowerRequired = createBoolean("controlLowerRequired");

    public final StringPath controlUpperActions = createString("controlUpperActions");

    public final NumberPath<Double> controlUpperLimit = createNumber("controlUpperLimit", Double.class);

    public final BooleanPath controlUpperRequired = createBoolean("controlUpperRequired");

    public final StringPath dataCollectionItemName = createString("dataCollectionItemName");

    public final StringPath dataCollectionItemTag = createString("dataCollectionItemTag");

    public final NumberPath<Double> dataCollectionItemTarget = createNumber("dataCollectionItemTarget", Double.class);

    public final StringPath dataCollectionSpecGroup = createString("dataCollectionSpecGroup");

    //inherited
    public final StringPath id = _super.id;

    public final StringPath linkKey = createString("linkKey");

    //inherited
    public final StringPath referenceKey = _super.referenceKey;

    public final StringPath screenLowerActions = createString("screenLowerActions");

    public final NumberPath<Double> screenLowerLimit = createNumber("screenLowerLimit", Double.class);

    public final BooleanPath screenLowerRequired = createBoolean("screenLowerRequired");

    public final StringPath screenUpperActions = createString("screenUpperActions");

    public final NumberPath<Double> screenUpperLimit = createNumber("screenUpperLimit", Double.class);

    public final BooleanPath screenUpperRequired = createBoolean("screenUpperRequired");

    public final StringPath specLowerActions = createString("specLowerActions");

    public final NumberPath<Double> specLowerLimit = createNumber("specLowerLimit", Double.class);

    public final BooleanPath specLowerRequired = createBoolean("specLowerRequired");

    public final StringPath specUpperActions = createString("specUpperActions");

    public final NumberPath<Double> specUpperLimit = createNumber("specUpperLimit", Double.class);

    public final BooleanPath specUpperRequired = createBoolean("specUpperRequired");

    public QCimDataCollectionSpecItemDO(String variable) {
        super(CimDataCollectionSpecItemDO.class, forVariable(variable));
    }

    public QCimDataCollectionSpecItemDO(Path<? extends CimDataCollectionSpecItemDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCimDataCollectionSpecItemDO(PathMetadata metadata) {
        super(CimDataCollectionSpecItemDO.class, metadata);
    }

}

