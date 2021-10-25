package com.github.code;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/25     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/25 13:28
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity extends BaseCimEntity {
}
