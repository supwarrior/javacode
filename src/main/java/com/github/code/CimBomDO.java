package com.github.code;

import com.github.annotation.MasterEntity;
import com.github.annotation.NamedIdentifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/25     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/25 19:59
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Embeddable
// @Entity
@Table(name = "OMBOM")
@Data
@MasterEntity
public class CimBomDO extends MainEntity {
    @Column(name = "BOM_ID", length = 64)
    @NamedIdentifier
    private String bomID;

    @Column(name = "DESCRIPTION", length = 128)
    private String description;
}
