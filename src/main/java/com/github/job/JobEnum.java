package com.github.job;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum JobEnum {
    /**
     * 简单任务
     */
    SIMPLE_JOB("SimpleJob", "SIMPLE"),

    /**
     * 数据流任务
     */
    DATAFLOW_JOB("DataflowJob", "DATAFLOW"),

    /**
     * 脚本任务
     */
    SCRIPT_JOB("ScriptJob", "SCRIPT");

    /**
     * 任务名称
     */
    private final String name;

    /**
     * 任务类型
     */
    private final String type;

    JobEnum(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * 返回任务类型名称列表
     * @return 任务类型名称列表
     */
    public static List<String> jobNameList() {
        return Arrays.stream(JobEnum.values()).map(JobEnum::getName).collect(Collectors.toList());
    }
}
