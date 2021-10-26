package com.github.code;

import com.github.annotation.IdPrefix;
import com.github.annotation.MasterEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 康盼Java开发工程师
 */
@EqualsAndHashCode(callSuper = true)
@Entity
//@Embeddable
@Table(name = "OMAMPLAN")
@IdPrefix(value = "OMAMPLAN")
@Data
@MasterEntity
public class CimEquipmentMonitorDO extends MainEntity {
    @Column(name = "EQP_ID", length = 64)
    private String equipmentID;
    @Column(name = "CHAMBER_ID", length = 64)
    private String chamberID;
    @Column(name = "CHAMBER_RKEY", length = 64)
    private String chamberObj;
}
