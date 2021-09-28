package com.github.dataInfo;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用于测试类生成的
 * @author 康盼Java开发工程师
 */
@Data
public class JavaClassInfo {
    /**
     * 当前被测试类的包名
     */
    private String packageName;

    /**
     * 当前被测试类的绝对路径
     */
    private String absolutePath;

    /**
     * 生成测试类的绝对路径
     */
    private String testAbsolutePath;

    /**
     * 被测试类的全限定名称
     */
    private String fullyTypeName;

    /**
     * 被测试类的类名
     */
    private String typeName;

    /**
     * 当前 类属性名称
     * 被测试的类名 - 首字母小写
     */
    private String modelNameLowerCamel;

    /**
     * 类信息存储
     * key - 类的全限定名称
     * value - 类信息
     */
    private Map<String, JavaClassModel> javaClassModelMap = new HashMap<>();

    /**
     * key - 属性变量名称
     * value - 属性类型的全限定名称
     */
    private Map<String, String> fieldFullyTypeNameMap = new HashMap<>();

    /**
     * key - 类型简称
     * value - 类型的全限定名称
     */
    private Map<String, String> fullyTypeNameMap = new HashMap<>();

    /**
     * mock的类信息
     * key - 属性变量名称 + "." + 方法名称（暂时不支持重名方法）
     * value - 属性类型的全限定名称
     */
    private Map<String, String> mockFullyTypeNameMap = new HashMap<>();

    /**
     * 需要导入的包  如果有多个，全部使用全限定名，在该map中的，表示没有简称相同的类
     * key - 变量名-简称
     * value - 全限定名称
     */
    private Map<String, Set<String>> implementsJavaPackageMap = new HashMap<>();

    /**
     * 测试方法名称出现的次数，如果有多个重名方法，方法后面接上数字
     */
    private Map<String, Integer> methodMap = new HashMap<>();
}
