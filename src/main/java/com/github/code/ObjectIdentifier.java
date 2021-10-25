package com.github.code;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 康盼Java开发工程师
 */
@Getter
@Setter
public class ObjectIdentifier {

    /**
     * 业务对象标识符ID
     */
    private String value;

    /**
     * 数据库主键ID
     */
    private String referenceKey;

    /**
     * 无参构造函数
     */
    public ObjectIdentifier() {

    }

    /**
     * 构造函数
     *
     * @param value        业务对象标识符ID
     * @param referenceKey 数据库主键ID
     */
    public ObjectIdentifier(String value, String referenceKey) {
        this.value = value;
        this.referenceKey = referenceKey;
    }

}
