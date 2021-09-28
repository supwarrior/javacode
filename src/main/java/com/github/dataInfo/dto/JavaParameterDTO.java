
package com.github.dataInfo.dto;

import com.github.dataInfo.BaseCanUserType;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法参数
 *
 * @author 康盼Java开发工程师
 */
@Data
@ToString(callSuper = true)
public class JavaParameterDTO extends BaseCanUserType {
    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数名称 - 首字母大写
     * 便于setter方法设置
     */
    private String upName;

    /**
     * 是否自定义类型(自定义类型-无法直接赋值基础类型的值)
     */
    private Boolean customType;

    /**
     * 默认值
     */
    private String value;

    /**
     * 如果默认值为NULL
     * 参数中的参数
     */
    private List<JavaParameterDTO> javaParameterDTOList = new ArrayList<>();

    /**
     * 当前属性的一个排序
     */
    private Integer order;


    /**
     * 参数所在的类全限定名称
     */
    private String classfullyType;
    /**
     * 参数所在的方法全限定名称
     */
    private String methodfullyType;
    /**
     * 参数所在的包的全限定名称
     */
    private String packageName;


    public void setValue(String value) {
        this.value = value;
    }
}
