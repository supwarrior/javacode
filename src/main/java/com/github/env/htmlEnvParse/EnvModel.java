package com.github.env.htmlEnvParse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvModel {

    /**
     * 原有名
     */
    private String evnId;

    /**
     * 新名
     */
    private String newEvnId;

    /**
     * 系统
     */
    private String systemName;

    /**
     * 操作
     */
    private String operation;

    /**
     * 描述
     */
    private String description;

    /**
     * 默认值
     */
    private String envValue;

    /**
     * sql
     */
    private String sql;
}
