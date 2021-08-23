package com.test;

import com.github.javabean.Beans;
import com.github.model.IUserService;
import com.github.model.User;
import com.github.model.UserServiceImpl;
import com.github.model.VipUserServiceImpl;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 */
public class UserTest {


    @Test
    public void test() {
        IUserService userService = (IUserService) Beans.getByType(VipUserServiceImpl.class);
        System.out.println(userService.getUser());
        User user = (User) Beans.getByType(User.class);
        System.out.println(user);
    }
}
