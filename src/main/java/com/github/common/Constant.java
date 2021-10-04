package com.github.common;

import java.time.format.DateTimeFormatter;

/**
 * 存储常量
 *
 * @author 康盼Java开发工程师
 */
public class Constant {
    /**
     * 项目文件路径
     */
    public static final String JAVA_MAIN_SRC = "/src/main/java/";
    /**
     * 当前系统
     */
    public static final String WINDOWS = "Windows";
    /**
     * 测试类路径
     */
    public static final String JAVA_TEST_SRC = "/src/test/java/";
    /**
     * 类后缀名
     */
    public static final String JAVA = ".java";
    /**
     * 类后缀名
     */
    public static final String DOT = ".";
    /**
     * 生成测试类的对应类名 后缀
     */
    public static final String TEST_CLASS_SUFFIX = "Test";
    /**
     * 模板文件路径
     */
    public static final String CONFIG_PATH = "/src/main/resources/template/";

    /**
     * The location to look for factories.
     * <p>Can be present in multiple JAR files.
     */
    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring-bean.factories";

    /**
     * bean存放的位置
     * eg:文件名：com.github.service.接口名 -> 内容：com.github.service.实现类
     */
    public static final String PREFIX = "META-INF/services/";

    /**
     * 运行项目的根路径
     */
    public static final String BASEDIR = System.getProperty("user.dir");

    /**
     * 线程安全的时间格式化 带时分秒
     */
    public static final DateTimeFormatter TIME_FORMATTER_HHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 线程安全的时间格式化 不带时分秒
     */
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
