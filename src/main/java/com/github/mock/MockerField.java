package com.github.mock;

import lombok.Data;

/**
 * Unit Test Model for Mock
 * private 属性 属性名称
 *
 * @author 康盼Java开发工程师
 */
@Data
public class MockerField {
    /**
     * 属性
     */
    private String typeName;
    /**
     * 属性名称
     */
    private String fieldName;
    /**
     * 属性包全限定名称
     */
    private String typeFullName;;
}
