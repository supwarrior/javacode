package com.github.mvc.service.impl;

import com.github.annotation.Component;
import com.github.javabean.Beans;
import com.github.mvc.service.IBeanService;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class BeanService implements IBeanService {

    @Override
    public void loadSpringFactoriesBean() throws Exception {
        Class.forName("com.github.javabean.BeanDriverManager");
    }

    @Override
    public void destroyBean() {
        Beans.destroy();
    }

    @Override
    public void initBean(String beanName) {
        Beans.init(beanName);
    }
}
