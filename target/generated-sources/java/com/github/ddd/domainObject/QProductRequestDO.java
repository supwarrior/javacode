package com.github.ddd.domainObject;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductRequestDO is a Querydsl query type for ProductRequestDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductRequestDO extends EntityPathBase<ProductRequestDO> {

    private static final long serialVersionUID = 807249582L;

    public static final QProductRequestDO productRequestDO = new QProductRequestDO("productRequestDO");

    public final QMainEntity _super = new QMainEntity(this);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath productRequestID = createString("productRequestID");

    public final NumberPath<Integer> requestNumber = createNumber("requestNumber", Integer.class);

    public QProductRequestDO(String variable) {
        super(ProductRequestDO.class, forVariable(variable));
    }

    public QProductRequestDO(Path<? extends ProductRequestDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductRequestDO(PathMetadata metadata) {
        super(ProductRequestDO.class, metadata);
    }

}

