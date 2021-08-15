package com.test.injectTest;

/**
 * @author 康盼Java开发工程师
 * @description 测试的接口实现类
 */
public class FileResource implements Resource {
    @Override
    public String getInputStream() {
        return "FileResource";
    }
}
