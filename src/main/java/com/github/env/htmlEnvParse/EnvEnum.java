package com.github.env.htmlEnvParse;

import lombok.Data;

/**
 * 环境变量脚本
 */
public enum EnvEnum {
    /**
     * 插入
     */
    INSERT("INSERT INTO OMENV(ID, ENV_ID, ENV_VALUE, DESCRIPTION) SELECT GET_ID('OMENV') AS ID, '%s', '%s', '%s' FROM DUAL; \n"),
    /**
     * 更新
     */
    UPDATE("UPDATE OMENV SET DESCRIPTION = '%s' WHERE ENV_ID = '%s'; \n"),

    UPDATE2("UPDATE OMENV SET ENV_ID = '%s' WHERE ENV_ID = '%s'; \n"),

    /**
     * 删除
     */
    DELETE("DELETE FROM OMENV WHERE ENV_ID = '%s'; \n"),

    /**
     * 序列
     */
    ID("SELECT GET_ID('OMENV') FROM DUAL; \n"),

    NOTHING("");

    private String sql;

    EnvEnum(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
