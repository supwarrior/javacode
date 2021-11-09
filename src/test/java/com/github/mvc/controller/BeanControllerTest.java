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
import com.github.mvc.model.oms.ComputerIntegratedManufacturingSystem;
import com.github.ddd.CoreBeanMapping;
import com.github.common.cons.MsgRetCodeConfig;
import com.github.ddd.BaseCore;

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
    
    @Mock
    private ComputerIntegratedManufacturingSystem computerIntegratedManufacturingSystem;
    
    @Mock
    private CoreBeanMapping coreBeanMapping;
    
    @Mock
    private MsgRetCodeConfig msgRetCodeConfig;
    
    @Mock
    private BaseCore baseCore;
    

    @Test
    public void msgRetCodeConfig(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.msgRetCodeConfig

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getBankInformation(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getBankInformation

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getLotBatchManagementFlow(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getLotBatchManagementFlow

        // 3.verify result
        // Assert.assertEquals
    }
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
    public void getConstraintListFlow(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getConstraintListFlow

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
    @Test
    public void getMdsFilters(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getMdsFilters

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getMdsJsonView(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getMdsJsonView

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getBayInfo(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getBayInfo

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getBankFlow(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getBankFlow

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getTerminology(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getTerminology

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void baseCoreInsert(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.baseCoreInsert

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getLotStartFlow(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getLotStartFlow

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getLotListFlow(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getLotListFlow

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getOmsFilters(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getOmsFilters

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getOmsJsonView(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getOmsJsonView

        // 3.verify result
        // Assert.assertEquals
    }
    @Test
    public void getEquipmentFlow(){
        // 1.set up
        // PowerMockito.when(模拟方法调用).thenReturn(模拟返回值);

        // 2.run test
        // BeanController.getEquipmentFlow

        // 3.verify result
        // Assert.assertEquals
    }
}