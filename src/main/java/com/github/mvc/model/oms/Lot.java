package com.github.mvc.model.oms;

/**
 * 一个批次号 对应的有基本信息 设备信息 流水线信息等
 *
 * @author 康盼Java开发工程师
 */
public class Lot {
    private String lotId;
    private String lotType;
    private String lastClaimedTime;
    /**
     * 等待时间
     */
    private String lagTime;
    private String lotStatus;
    private String waferQty;
    private String subLotType;
    private String bondingGroup;
    /**
     * 	Front Opening Unified Pod	前端开口传送盒	半导体制程中被使用来保护、运送、并储存晶圆的一种容器
     */
    private String carrierCategory;
    private String autoMonitorJob;

}
