package com.github.ddd.domainObject;

import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@MasterEntity
@Table(name = "t_product_request")
@ToString(callSuper = true)
@IdPrefix(value = "k")
public class ProductRequestDO extends MainEntity {
    @NamedIdentifier
    @Column(name = "PROD_ORDER_ID", length = 64, unique = true)
    private String productRequestID;
}
