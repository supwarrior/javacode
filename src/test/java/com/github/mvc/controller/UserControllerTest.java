/*
* com.github
*/
package com.github.mvc.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.ArgumentMatchers.any;

/**
* UserControllerTest
*
* @author k
* @date 2022
*/
@RunWith(PowerMockRunner.class)
// @PrepareForTest({XX.class}) 模拟final, private, static, native方法的类
@PowerMockIgnore("javax.management.*")
public class UserControllerTest {

    @InjectMocks
    private UserController userController;


    @Test
    public void getUserName(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserController.getUserName

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getRootUserName(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserController.getRootUserName

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getSuperUserName(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserController.getSuperUserName

        // 3.verify result
        // Assert.assertEquals
    }
}