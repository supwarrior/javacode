package com.github.mock;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * Unit Test Model for Mock
 *
 * @author 康盼Java开发工程师
 */
@Data
public class Mocker {
    /**
     * 头部描述
     */
    private String headDesc;
    /**
     * 测试类包名
     */
    private String packageName;
    /**
     * 测试类名 首字母大写
     */
    private String testClassName;
    /**
     * 源类名 被测试的类名  首字母大写
     */
    private String className;
    /**
     * 源类名 被测试的类名  首字母小写
     */
    private String lowerClassName;
    /**
     * 作者名
     */
    private String author;
    /**
     * 日期
     */
    private String date;
    /**
     * 生成的文件名
     */
    private File file;
    /**
     * mock引入的类
     */
    private List<MockerField> mockerFields;
    /**
     * mock 引入的方法
     */
    private List<MockerMethod> mockerMethods;
}
