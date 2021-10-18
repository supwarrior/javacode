package com.github.mvc.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author 康盼Java开发工程师
 */
@Data
public class TextValue {
    @JSONField(ordinal = 1)
    private String text;
    @JSONField(ordinal = 2)
    private String value;

    public TextValue(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public TextValue() {
        this.text = text;
        this.value = value;
    }
}
