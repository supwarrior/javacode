package com.github.mvc.service;

/**
 * @author 康盼Java开发工程师
 */
public interface IUserService {

    /**
     * 获取用户名
     * 
     * @return String
     */
    String getUserName();

    /**
     * 获取用户名
     *
     * @return String
     */
    String getSuperUserName();

    /**
     * 获取 root 用户名
     *
     * @return String
     */
    String getRootUserName();
}
