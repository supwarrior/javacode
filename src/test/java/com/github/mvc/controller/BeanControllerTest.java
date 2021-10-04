/*
* com.github
*/
package com.github.mvc.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
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
@PowerMockIgnore("javax.management.*")
public class BeanControllerTest {

    @InjectMocks
    private BeanController BeanController;

    @Mock
    private IBeanService beanService;

    @Test
    public void initializeAndDestroy(){
        // 1.set up

        // 2.run test

        // 3.verify result
    };
    @Test
    public void loadSpringFactoriesBean(){
        // 1.set up

        // 2.run test

        // 3.verify result
    };
}