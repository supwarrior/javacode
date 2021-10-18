package com.github.mvc.model.sps;

import lombok.Data;

/**
 * 订单排期计划服务系统中的 Product
 *
 * @author 康盼Java开发工程师
 */
@Data
public class Product {
    /**
     * 产线id 对应多个批次号
     */
    private String productId;
    /**
     * 层次等级
     */
    private String layer;
    /**
     * 产品类型
     */
    private String prodType;
    /**
     * 调度类型 by volume / by source lot
     * 源材料/继承批次号
     */
    private String schedulingType;
    /**
     * 工序流程
     */
    private String processFlow;
    /**
     * 描述
     */
    private String description;
}
