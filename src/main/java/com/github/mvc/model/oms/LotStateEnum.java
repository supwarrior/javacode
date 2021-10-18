package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */
public enum LotStateEnum {

    LOT_STATE("批次状态"),

    LOT_PRODUCTION_STATE("批次产品状态"),

    LOT_HOLD_STATE("批次暂停状态"),

    LOT_FINISHED_STATE("批次完成状态"),

    LOT_PROCESS_STATE("批次生产状态"),

    LOT_INVENTORY_STATE("批次库存状态");

    private String description;

    LotStateEnum(String description) {
        this.description = description;
    }
}
