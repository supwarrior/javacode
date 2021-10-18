package com.github.mvc.model.sps;

import lombok.Data;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Data
public class SchedulingPlan {
    private String productId;
    private String schedulingMode;
    private int totalFinishPQ;
    private String processFlow;
    private String roundPQMode;
    private String schedulingType;
    /**
     * product quantity 生产下的量
     */
    private int totalStartPQ;
    private int maxLotSize;
    private String distributionMode;
    private List<SchedulingPlanLotInfo> schedulingPlanLotInfoList;
}
