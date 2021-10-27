package com.github.common.cons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
@PropertySource("classpath:msg.properties")
@ConfigurationProperties(prefix="msg")
@Setter
@Getter
public class MsgRetCodeConfig {
    /**
     * MSG.MSG_OK=(0,"ok")
     */
    private Code msgOk;
}
