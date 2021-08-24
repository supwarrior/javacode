package com.github.model;

import com.github.annotation.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class VipUserServiceImpl implements IUserService {

    private User user;

    @Override
    public User getUser() {
        return user;
    }
}
