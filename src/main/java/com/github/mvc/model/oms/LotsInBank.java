package com.github.mvc.model.oms;

import lombok.Data;

/**
 * @author 康盼Java开发工程师
 */
@Data
public class LotsInBank {
    private String bankId;
    /**
     * 批次编码
     */
    private String LotId;
    /**
     * 批次类型
     */
    private String lotType;
    /**
     * 状态
     */
    private String status;
    /**
     * Wafer数量
     */
    private String waferCount;
    /**
     * 晶舟编码
     */
    private String carrierId;
    /**
     * 产品编码
     */
    private String prodId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 客户编码
     */
    private String customerId;
    /**
     * 到期时间
     */
    private String dueTime;
    /**
     * 最后修改时间
     */
    private String lastTransTime;
}
