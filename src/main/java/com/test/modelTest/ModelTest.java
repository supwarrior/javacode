package com.test.modelTest;


import com.github.annotation.Inject;
import com.github.javabean.BeanInject;
import com.github.model.User;
import org.junit.Test;


/**
 * @author 康盼Java开发工程师
 */
public class ModelTest {

    @Inject
    private User user;

    @Test
    public void test() {
        ModelTest modelTest = new ModelTest();
        BeanInject.set(modelTest);
        System.out.println(modelTest.user);

    }
}
