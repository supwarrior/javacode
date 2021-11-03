package com.github.ddd.businessObject;

import com.github.annotation.Core;
import com.github.ddd.domainObject.ProductRequestDO;

@Core
public class ProductRequestBO extends AbstractProductRequest<ProductRequestDO> implements ProductRequest {

    public ProductRequestBO(ProductRequestDO entity) {
        super(entity);
    }
}
