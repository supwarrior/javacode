package com.github.mvc.service.impl;

import com.github.javabean.BeanLifecycle;
import com.github.javabean.Beans;
import com.github.mvc.model.RootUser;
import com.github.mvc.model.SuperUser;
import com.github.mvc.model.User;
import com.github.mvc.service.IUserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 这里演示不使用 @Component 去注入 InjectHandler去处理
 *
 * @author 康盼Java开发工程师
 */
@Slf4j
public class UserService implements IUserService, BeanLifecycle {
    /**
     * 这里没有注入 默认使用的是Inject注入
     */
    private User user;

    /**
     * 使用 @Inject 注解可以不用在 bean.xml 配置 ref properties
     * <property name="rootUser" class="com.github.mvc.model.RootUser" ref="rootUser"></property>
     * 将在 BeanInject.set(bean) 注入
     */
    private RootUser rootUser;

    @Override
    public String getUserName() {
        return user.getName();
    }

    @Override
    public String getRootUserName() {
        return rootUser.getName();
    }

    @Override
    public String getSuperUserName() {
        SuperUser superUser = (SuperUser) Beans.getByName("superUser");
        return superUser.getName();
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
