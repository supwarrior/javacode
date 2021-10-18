package com.github.mvc.model.sps;

import lombok.Data;

/**
 * @author 康盼Java开发工程师
 */
@Data
public class ProductInfo {
    /**
     * productId
     */
    private String productId;
    private String prodType;
    /**
     * 描述
     */
    private String description;
    /**
     * 工序流程
     */
    private String processFlow;
    /**
     * 子批次类型
     */
    private String subLotType;
    /**
     * FA IBM
     */
    private String customerCode;
    private String schedulingPlanType;
    private String layer;
    /**
     * weekly daily
     */
    private String schedulingType;
    /**
     * 批次类别
     */
    private String lotType;
    private String lotIdHeader;
    private String lotOwner;
    private String schedulingMode;
}
