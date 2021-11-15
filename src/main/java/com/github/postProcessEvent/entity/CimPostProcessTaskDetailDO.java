package com.github.postProcessEvent.entity;

import com.github.annotation.IdPrefix;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
        name = "OQPPTASK_DETAIL"
)
@IdPrefix("OQPPTASK_DETAIL")
@Data
public class CimPostProcessTaskDetailDO extends NonRuntimeEntity {
    @Column(
            name = "REFKEY"
    )
    private String refKey;
    @Column(
            name = "IDX_NO"
    )
    private Integer indexNo;
    @Column(
            name = "NAME"
    )
    private String name;
    @Column(
            name = "VALUE"
    )
    private String value;
}
