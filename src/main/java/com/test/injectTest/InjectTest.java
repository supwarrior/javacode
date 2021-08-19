package com.test.injectTest;

import com.github.annotation.Inject;
import com.github.javabean.BeanAop;
import org.junit.Test;

/**
 * @author 康盼Java开发工程师
 * @description inject 测试类
 */
public class InjectTest implements DemoTest {

    @Inject("injectTestPrint")
    private TestPrint testPrint;

    @Test
    public void test() {
        DemoTest demoTest = (DemoTest) BeanAop.instance(InjectTest.class);
        demoTest.getTestPrint().print();
    }

    @Override
    public TestPrint getTestPrint() {
        return testPrint;
    }

}
