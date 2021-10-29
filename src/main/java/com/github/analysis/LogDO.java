package com.github.analysis;

import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import com.github.ddd.domainObject.MainEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 康盼Java开发工程师
 */
@Entity
@Data
@MasterEntity
@Table(name = "t_log")
@ToString(callSuper = true)
@IdPrefix(value = "log")
public class LogDO extends MainEntity {

    @Column(name = "transaction_Id", length = 100)
    private String transactionId;

    @Column(name = "request_Time", length = 64)
    private String requestTime;

    @Column(name = "method_Name", length = 32)
    private String methodName;

    @Column(name = "time", length = 64)
    private String time;

}