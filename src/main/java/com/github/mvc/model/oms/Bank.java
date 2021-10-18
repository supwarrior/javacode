package com.github.mvc.model.oms;

import lombok.Data;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Data
public class Bank {
    /**
     * 仓库编码 对应多个批次号
     */
    private String bankId;
    private List<String> lotId;
    /**
     * 仓库信息描述
     */
    private String description;
    /**
     * 接收 是否可以接受物料
     */
    private String receive;
    /**
     * 出货 是否可以发货
     */
    private String ship;
    /**
     * 是否可以开始生产批次
     */
    private String lotStart;
    /**
     * （逻辑性的）入库 是否可以移动进入仓库
     */
    private String bankIn;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 是否为产品仓库
     */
    private String productBank;
    /**
     * 可循环利用 是否可回收
     */
    private String recycle;
    /**
     * Non Production Wafer 非生产批
     */
    private String npw;

}
