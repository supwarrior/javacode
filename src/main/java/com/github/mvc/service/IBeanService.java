package com.github.mvc.service;

/**
 * @author 康盼Java开发工程师
 */
public interface IBeanService {

    /**
     * 加载 Spring.factories Bean
     *
     * @throws Exception
     */
    void loadSpringFactoriesBean() throws Exception;

    /**
     * destroyBean
     */
    void destroyBean();

    /**
     * initBean
     * @param beanName
     */
    void initBean(String beanName);
}
