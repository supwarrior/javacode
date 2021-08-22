package com.test;

import com.github.javabean.Beans;
import com.github.model.IUserService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 */
public class UserTest {


    @Test
    public void test() {
        IUserService userService = (IUserService) Beans.getByType(IUserService.class);
        Assert.assertNotNull(userService.getUser());
    }
}
