package com.github.mvc.model.oms;

import com.github.mvc.model.TextValue;

import java.util.LinkedList;
import java.util.List;

/**
 * Bank Information
 *
 * @author 康盼Java开发工程师
 */
public enum LogInformationEnum {
    BANK_ID("Bank_Information", "仓库编码"),
    DESCRIPTION("Bank_Information", "仓库信息描述"),
    RECEIVE("Bank_Information", "是否可以接受物料"),
    SHIP("Bank_Information", "是否可以发货"),
    LOT_START("Bank_Information", "是否可以开始生产批次"),
    BANK_IN("Bank_Information", "是否可以移动进入仓库"),
    PRODUCT_TYPE("Bank_Information", "产品类型"),
    PRODUCT_BANK("Bank_Information", "是否为产品仓库"),
    RECYCLE("Bank_Information", "是否可回收"),
    NPW("Bank_Information", "是否为非生产批"),
    LOT_ID("Lots_In_Bank", "批次编码"),
    LOT_TYPE("Lots_In_Bank", "批次类型"),
    STATUS("Lots_In_Bank", "状态"),
    WAFER_COUNT("Lots_In_Bank", "Wafer数量"),
    CARRIER_ID("Lots_In_Bank", "晶舟编码"),
    PROD_ID("Lots_In_Bank", "产品编码"),
    ORDER_NO("Lots_In_Bank", "订单号"),
    CUSTOMER_ID("Lots_In_Bank", "客户编码"),
    DUE_TIME("Lots_In_Bank", "到期时间"),
    LAST_TRANS_TIME("Lots_In_Bank", "最后修改时间");
    private String description;
    private String key;

    LogInformationEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getKey() {
        return key;
    }

    public static List<TextValue> getLogInformationEnum(String key) {
        List<TextValue> result = new LinkedList<>();
        for (LogInformationEnum logInformationEnum :LogInformationEnum.values()) {
            if(logInformationEnum.getKey().equals(key)) {
                TextValue textValue = new TextValue(logInformationEnum.name(),logInformationEnum.getDescription());
                result.add(textValue);
            }
        }
        return result;
    }
}
