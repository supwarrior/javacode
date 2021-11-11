package com.github.common.cons;

import lombok.Getter;

@Getter
public enum CodeEnum {

    // 系统error
    SYSTEM_ERROR("-1"),
    // 参数错误
    PARAM_CHECK_ERROR("900002"),
    // Url 不存在
    URL_NOT_FOUND("900003"),
    // 数据不存在
    DATA_NOT_EXIST("900004"),
    // 没有权限
    NOT_AUTH("900005"),
    // 用户不存在
    USER_NOT_EXIST("900006"),
    // 密码错误
    PWD_ERROR("900007"),
    PWD_OVER("900008"),
    // 没有查询事务
    TRAN_NOT_QUERY("900009"),
    // 数据错误
    DATA_ERROR("900010"),
    // Lock 重复
    LOCK_UN("900011"),
    // Lock Id 不存在
    LOCK_ID_NOT_EXIST("900012"),
    // 模糊匹配
    MATCH_PARAM_NOT_EXIST("900013"),

    // 设备提示信息 100100 - 100199
    EQP_NOT_FOUND("100100"),
    // 设备不是串联模式
    EQP_RELATIONSHIP_NOT_SERIAL("100101"),
    // 设备的chamber不是一个有效的排序
    EQP_CHAMBER_FLOW_NOT_SORT("100102"),

    // chamber level recipe 100200-100299
    // chamber level recipe 不存在
    CHAMBER_RCP_EXIST("100200"),
    // 没有可用的chamber level recipe
    CHAMBER_RCP_NOT_USE("100201"),

    // furnace recipe 100300 - 100399
    LAYOUT_SPECIFIC_CONTROL_RESERVE_ERROR("100300"),
    // limit ， lower > upper 报错
    LAYOUT_RECIPE_LIMIT_INVALID_ERROR("100301"),
    // layout control 类型错误
    INVALID_LAYOUT_CONTROL_CATEGORY_ERROR("100302"),
    // layout recipe not found
    LAYOUT_RECIPE_NOT_FOUND("100303"),
    ;

    /**
     * code
     */
    private final String code;

    CodeEnum(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}
