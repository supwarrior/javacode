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
import com.github.mvc.service.IUserService;

/**
* UserControllerTest
*
* @author k
* @date 2022
*/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class UserControllerTest {

    @InjectMocks
    private UserController UserController;

    @Mock
    private IUserService userService;

    @Test
    public void getUserName(){
        // 1.set up

        // 2.run test

        // 3.verify result
    };
}