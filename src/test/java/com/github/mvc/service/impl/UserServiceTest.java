/*
* com.github
*/
package com.github.mvc.service.impl;

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
import com.github.mvc.model.User;

/**
* UserServiceTest
*
* @author k
* @date 2022
*/
@RunWith(PowerMockRunner.class)
// @PrepareForTest({XX.class}) 模拟final, private, static, native方法的类
@PowerMockIgnore("javax.management.*")
public class UserServiceTest {

    @InjectMocks
    private UserService UserService;

    @Mock
    private User user;

    @Test
    public void getProperties(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserService.getProperties

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getUserName(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserService.getUserName

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void initDataMethod(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserService.initDataMethod

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void destroyDataMethod(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // UserService.destroyDataMethod

        // 3.verify result
        // Assert.assertEquals
    }
}