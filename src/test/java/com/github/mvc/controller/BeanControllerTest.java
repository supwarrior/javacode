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
import com.github.mvc.service.IBeanService;

/**
* BeanControllerTest
*
* @author k
* @date 2022
*/
@RunWith(PowerMockRunner.class)
// @PrepareForTest({XX.class}) 模拟final, private, static, native方法的类
@PowerMockIgnore("javax.management.*")
public class BeanControllerTest {

    @InjectMocks
    private BeanController beanController;

    @Mock
    private IBeanService beanService;
    

    @Test
    public void initializeAndDestroy(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.initializeAndDestroy

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void loadSpringFactoriesBean(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.loadSpringFactoriesBean

        // 3.verify result
        // Assert.assertEquals
    }
}