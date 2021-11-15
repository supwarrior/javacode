package com.github.postProcessEvent.entity;

import com.github.annotation.IdPrefix;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "OQPPTASK")
@IdPrefix("OQPPTASK")
@Data
public class CimPostProcessTaskDO extends NonRuntimeEntity {

    @Column(
            name = "TASK_ID"
    )
    private String taskId;
    @Column(
            name = "IDX_NO"
    )
    private Integer indexNumber;
    @Column(
            name = "TRX_ID"
    )
    private String transactionId;
    @Column(
            name = "TASK_STATUS"
    )
    private String taskStatus;
    @Column(
            name = "EXECUTOR_ID"
    )
    private String executorId;
    @Column(
            name = "JOINED_MODE"
    )
    private String joinMode;
    @Column(
            name = "ERROR_MODE"
    )
    private String errorMode;
    @Column(
            name = "COMMIT_MODE"
    )
    private String commitMode;
    @Column(
            name = "ENTITY_TYPE"
    )
    private String entityType;
    @Column(
            name = "ENTITY_ID"
    )
    private String entityId;
    @Column(
            name = "EQP_ID"
    )
    private String equipmentId;
    @Column(
            name = "EQP_RKEY"
    )
    private String equipmentRkey;
    @Column(
            name = "ENTITY_RKEY"
    )
    private String entityRkey;
    @Column(
            name = "CHAINED_FLAG"
    )
    private Boolean chainedFlag;
    @Column(
            name = "CJ_ID"
    )
    private String controJobId;
    @Column(
            name = "CJ_RKEY"
    )
    private String controlJobRkey;
    @Column(
            name = "CREATE_TIME"
    )
    private Timestamp createTime;
    @Column(
            name = "UPDATE_TIME"
    )
    private Timestamp updateTime;
    @Column(
            name = "TRX_USER_ID"
    )
    private String trxUserId;
    @Column(
            name = "TRX_TIME"
    )
    private Timestamp trxTime;
    @Column(
            name = "TRX_MEMO"
    )
    private String trxMemo;
}
