package com.github.mvc.model.oms;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


/**
 * 计算机集成制造系统 包含的组件
 *
 * @author 康盼Java开发工程师
 */
@Data
public class SystemComponent {
    /**
     * 备注
     */
    @JSONField(ordinal = 0)
    private int index;
    /**
     * 组件所在的模块名称 JSONField 转换 json 排序
     */
    @JSONField(ordinal = 1)
    private String module;
    /**
     * 功能
     */
    @JSONField(ordinal = 2)
    private String function;
    /**
     * 术语
     */
    @JSONField(ordinal = 3)
    private String term;
    /**
     * 中文术语
     */
    @JSONField(ordinal = 4)
    private String chineseTerm;
    /**
     * 描述
     */
    @JSONField(ordinal = 5)
    private String description;
    /**
     * 备注
     */
    @JSONField(ordinal = 6)
    private String remarks;
}
