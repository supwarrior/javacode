package com.github.service.impl;

import com.github.model.User;
import com.github.service.IUserService;

/**
 * @author 康盼Java开发工程师
 */
public class VipUserServiceImpl implements IUserService {

    private User user;

    @Override
    public User getUser() {
        return user;
    }
}
