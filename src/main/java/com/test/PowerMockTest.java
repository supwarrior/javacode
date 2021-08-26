package com.test;

import com.github.model.IUserService;
import com.github.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author 康盼Java开发工程师
 */
@RunWith(PowerMockRunner.class)
public class PowerMockTest {

    @Mock
    private IUserService userService;


    @Test
    public void getUser() {
        User user = new User();
        PowerMockito.when(userService.getUser()).thenReturn(user)
                .thenReturn(null).thenThrow(new RuntimeException("第三次调用异常"));
        Assert.assertNotNull(userService.getUser());
        Assert.assertNull(userService.getUser());
        try {
            userService.getUser();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
