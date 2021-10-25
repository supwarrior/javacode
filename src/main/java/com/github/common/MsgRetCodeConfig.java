package com.github.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/21     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/21 20:57
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Component
@PropertySource("classpath:msg.properties")
@ConfigurationProperties(prefix="msg")
@Setter
@Getter
public class MsgRetCodeConfig {
    /**
     * MSG.MSG_OK=(0,"Normal end.")
     */
    private Code msgOk;
}
