package com.github.postProcessEvent.entity;

import com.github.annotation.IdPrefix;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 后置事务处理器定义
 */
@Entity
@Table(
        name = "OSPPTRXDEF"
)
@IdPrefix("OSPPTRXDEF")
@Data
public class CimPostProcessTransactionDefinitionDO extends NonRuntimeEntity {

    @Column(
            name = "TRX_ID"
    )
    private String transactionId;
    @Column(
            name = "TRX_NAME"
    )
    private String transactionName;
    @Column(
            name = "PATTERN_ID"
    )
    private Integer patternId;
}
