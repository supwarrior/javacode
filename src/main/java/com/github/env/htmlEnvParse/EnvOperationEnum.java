package com.github.env.htmlEnvParse;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum EnvOperationEnum {

    /**
     * 优先级 3
     */
    INSERT("新增;增加"), // 包含这些，可能是更新语句，先判断是不是更新，不是则插入
    /**
     * 优先级 2
     */
    UPDATE("添加备注;更新;增加备注"), // 包含这些，则是更新语句
    /**
     * 优先级 1
     */
    DELETE("删除"), // 有删除，则必然是删除
    /**
     * 优先级 1
     */
    NOTHING("不清楚;不存在;不使用;未使用"); // 如果不包含新增，则不做任何事情

    private String operation;

    EnvOperationEnum(String operation) {
        this.operation = operation;
    }

    public static String getOperationSql(String sqlOperation, EnvModel envModel, boolean flag) {
        if (Arrays.stream(DELETE.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))) {
            if (StringUtils.isNotBlank(envModel.getEvnId())) {
                return String.format(EnvEnum.DELETE.getSql(), envModel.getEvnId());
            }
        } else if (Arrays.stream(NOTHING.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))
                && Arrays.stream(INSERT.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))) {
            return String.format(EnvEnum.INSERT.getSql(), envModel.getEvnId(), envModel.getEnvValue(), envModel.getDescription());

        } else if (Arrays.stream(NOTHING.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))
                && !Arrays.stream(INSERT.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))) {
            return EnvEnum.NOTHING.getSql();

        } else if (Arrays.stream(UPDATE.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))) {
            if (!flag) {
                if (envModel.getDescription().length() < 512
                        && !StringUtils.isAnyBlank(envModel.getEvnId(), envModel.getDescription())) {
                    return String.format(EnvEnum.UPDATE.getSql(), envModel.getDescription(), envModel.getEvnId());
                } else if (envModel.getDescription().length() >= 512
                        && !StringUtils.isAnyBlank(envModel.getEvnId(), envModel.getDescription())) {
                    return String.format(EnvEnum.UPDATE.getSql(), "", envModel.getEvnId());
                }
            } else {
                return String.format(EnvEnum.UPDATE2.getSql(), envModel.getNewEvnId(), envModel.getEvnId());
            }

        } else if (Arrays.stream(INSERT.operation.split(";")).anyMatch(ele -> sqlOperation.contains(ele))) {
            if (envModel.getDescription().length() < 512) {
                return String.format(EnvEnum.INSERT.getSql(), envModel.getEvnId(), envModel.getEnvValue(), envModel.getDescription());
            } else if (envModel.getDescription().length() >= 512) {
                return String.format(EnvEnum.INSERT.getSql(), envModel.getEvnId(), envModel.getEnvValue(), "");
            }

        }
        return EnvEnum.NOTHING.getSql();
    }
}
