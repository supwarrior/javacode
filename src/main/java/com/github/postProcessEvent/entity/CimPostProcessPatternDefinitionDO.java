package com.github.postProcessEvent.entity;

import com.github.annotation.IdPrefix;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 后置处理模式定义
 */
@Entity
@Table(
        name = "OSPPPTNDEF"
)
@IdPrefix("OSPPPTNDEF")
@Data
public class CimPostProcessPatternDefinitionDO extends NonRuntimeEntity {

    @Column(
            name = "PATTERN_ID"
    )
    private String patternId;
    @Column(
            name = "IDX_NO"
    )
    private Integer indexNumber;
    @Column(
            name = "EXECUTOR_ID"
    )
    private String executorId;
    @Column(
            name = "JOINED_MODE"
    )
    private String joinMode;
    @Column(
            name = "CHAINED_FLAG"
    )
    private Boolean chainedFlag;
    @Column(
            name = "ENTITY_TYPE"
    )
    private String entityType;
    @Column(
            name = "ERROR_MODE"
    )
    private String errorModeName;
    @Column(
            name = "COMMIT_MODE"
    )
    private String commitModeName;
}
