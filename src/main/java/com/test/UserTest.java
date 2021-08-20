package com.test;

import com.github.javabean.Beans;
import com.github.model.IUserService;
import com.github.model.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 */
public class UserTest {


    @Test
    public void test() {
        IUserService userService = (IUserService) Beans.getByType(UserServiceImpl.class);
        Assert.assertNotNull(userService.getUser());
    }
}
