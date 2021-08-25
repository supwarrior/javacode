package com.github.model;

import com.github.annotation.Component;
import com.github.annotation.Inject;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class UserServiceImpl implements IUserService {

    @Inject
    private User user;

    @Override
    public User getUser() {
        return user;
    }
}
