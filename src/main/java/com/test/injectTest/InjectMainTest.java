package com.test.injectTest;

import com.github.annotation.Inject;
import com.github.javabean.BeanInject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 * @description inject 测试类
 */
public class InjectMainTest {

    @Inject
    private Resource resource;

    @Test
    public void test() {
        InjectMainTest injectMainTest = new InjectMainTest();
        BeanInject.set(injectMainTest);
        String result = injectMainTest.getResource().getInputStream();
        Assert.assertEquals(result, "FileResource");
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
