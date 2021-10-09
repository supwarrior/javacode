package com.github.mvc.service.impl;

import com.github.javabean.BeanLifecycle;
import com.github.javabean.Beans;
import com.github.mvc.model.User;
import com.github.mvc.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 这里演示不使用 @Component 去注入 InjectHandler去处理
 *
 * @author 康盼Java开发工程师
 */
@Slf4j
public class UserService implements IUserService, BeanLifecycle {
    
    @Autowired
    private User user;

    @Override
    public String getUserName() {
        user = (User) Beans.getByName("user");
        return user.getName();
    }

    /**
     * 调用接口 http://localhost:8028/api/bean/loadSpringFactoriesBean
     * 测试 JSON.toJSONString 证明toJSONString的时候会调用getX方法返回的值
     *
     * @return String
     */
    public String getProperties() {
        return "-1";
    }

    @Override
    public void initDataMethod() {
        log.info("initDataMethod");
    }

    @Override
    public void destroyDataMethod() {
        log.info("destroyDataMethod");
    }
}
