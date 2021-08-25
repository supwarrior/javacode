package com.test;

import com.github.javabean.BeanDriverManager;
import com.github.javabean.Beans;
import com.github.model.IUserService;
import com.github.model.User;
import com.github.model.UserServiceImpl;
import com.github.model.VipUserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 */
public class AllTest {


    @Test
    public void getByTypeForUser() {
        User user = (User) Beans.getByType(User.class);
        System.out.println(user);
    }

    @Test
    public void getByNameForUser() {
        User user = (User) Beans.getByName("user");
        System.out.println(user);
    }

    @Test
    public void getByTypeForUserServiceImpl() {
        IUserService userService = (UserServiceImpl) Beans.getByType(UserServiceImpl.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void getByNameForUserServiceImpl() {
        IUserService userService = (UserServiceImpl) Beans.getByName("userServiceImpl");
        System.out.println(userService.getUser());
    }


    @Test
    public void getByTypeForVipUserServiceImpl() {
        IUserService userService = (VipUserServiceImpl) Beans.getByType(VipUserServiceImpl.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void getByNameForVipUserServiceImpl() {
        IUserService userService = (VipUserServiceImpl) Beans.getByName("vipUserServiceImpl");
        System.out.println(userService.getUser());
    }

    @Test
    public void getByTypeForIUserService() {
        IUserService userService = (IUserService) Beans.getByType(IUserService.class);
        System.out.println(userService.getUser());
    }

    @Test
    public void classForNameTest() throws Exception {
        Class.forName("com.github.javabean.BeanDriverManager");
        BeanDriverManager manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Beans.getByType(UserServiceImpl.class);
        Beans.getByType(VipUserServiceImpl.class);
        Beans.getByType(User.class);
        manager.printAllBean();
        manager = (BeanDriverManager) Beans.cache.get("beanDriverManager");
        Assert.assertNull(manager);
    }

}
