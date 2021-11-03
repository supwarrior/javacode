package com.github.common.cons;

/**
 * 全局的跟踪 id
 *
 * i.1:代表系统
 * ii.2-4位为功能模块，
 * iii.5位为接口属性（W增删改，Q查询，R回调）
 * iv.6-8位为编号
 *
 * @author 康盼Java开发工程师
 */
public enum TransactionIDEnum {

    NULL(null),
    JCW01("JCW01"),
    JCW02("JCW02");


    private String value;
    TransactionIDEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static TransactionIDEnum get(String value) {
        if (value instanceof String) {
            for (TransactionIDEnum transactionIDEnum : TransactionIDEnum.values()) {
                if (value.equals(transactionIDEnum.getValue())) {
                    return transactionIDEnum;
                }
            }
        }
        return NULL;
    }

}
