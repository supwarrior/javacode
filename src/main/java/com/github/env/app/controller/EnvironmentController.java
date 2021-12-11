package com.github.env.app.controller;

import com.github.env.StandardProperties;
import com.github.env.app.entity.EnvVariable;
import com.github.env.app.service.ISystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试环境变量的值
 */
@RestController
@RequestMapping("/env")
@Slf4j
public class EnvironmentController {

    @Autowired
    private ISystemService systemService;

    @GetMapping("/get")
    public String getEnv() throws Exception{
        String orValue = StandardProperties.OM_PROC_MON_WAIT_HOLD_LOGIC.getValue();
        log.info("环境变量之前的值 {}", orValue);

        // 这里模拟前端修改值
        EnvVariable envVariable = new EnvVariable();
        envVariable.setEnvName("OM_PROC_MON_WAIT_HOLD_LOGIC");
        envVariable.setEnvValue("1");
        systemService.envModifyReq(envVariable);

        Thread.sleep(3000);
        String value = StandardProperties.OM_PROC_MON_WAIT_HOLD_LOGIC.getValue();
        log.info("环境变量之后的值", value);
        return value;
    }
}
