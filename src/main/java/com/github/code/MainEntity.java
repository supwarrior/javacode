package com.github.code;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

/**
 * description:
 * <p>MasterEntity .<br/></p>
 * <p>
 * change history:
 * date             defect#             person             comments
 * -------------------------------------------------------------------------------------------------------------------
 * 2019/9/3         ********             ZQI               create file
 *
 * @author ZQI
 * @date 2019/9/3 14:58
 * @copyright 2019, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class MainEntity extends BaseEntity {

    private static final long serialVersionUID = 7872938649139372825L;
}
