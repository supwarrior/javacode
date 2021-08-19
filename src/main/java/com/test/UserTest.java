package com.test;

import com.github.annotation.Inject;
import com.github.javabean.BeanInject;
import com.github.javabean.Beans;
import com.github.model.User;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 */
public class UserTest {

    @Inject
    private User user;
    
    @Test
    public void test() {
        UserTest test = new UserTest();
        BeanInject.set(test);
        System.out.println(test.user);
    }
}
