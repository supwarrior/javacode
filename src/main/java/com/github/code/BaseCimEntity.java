package com.github.code;

import javax.persistence.Column;
import javax.persistence.Id;

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

public abstract class BaseCimEntity implements BaseCimET {

    private static final long serialVersionUID = 2938092512338745974L;
    @Id
    @Column(
            name = "ID"
    )
    private Long id;

    public BaseCimEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
