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

/**
* BeanServiceTest
*
* @author k
* @date 2022
*/
@RunWith(PowerMockRunner.class)
// @PrepareForTest({XX.class}) 模拟final, private, static, native方法的类
@PowerMockIgnore("javax.management.*")
public class BeanServiceTest {

    @InjectMocks
    private BeanService BeanService;


    @Test
    public void destroyBean(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanService.destroyBean

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void initBean(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanService.initBean

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void loadSpringFactoriesBean(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanService.loadSpringFactoriesBean

        // 3.verify result
        // Assert.assertEquals
    }
}