package com.github.code;

import java.io.Serializable;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/25     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/25 13:29
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */

public interface BaseCimET extends Serializable  {

    Long getId();

    void setId(Long id);
}
