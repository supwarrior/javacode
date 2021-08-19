package com.test.injectTest;

/**
 * @author 康盼Java开发工程师
 * @description 测试打印
 */
public class InjectTestPrint implements TestPrint {

    @Override
    public void print() {
        System.out.printf("InjectTestPrint");
    }
}
