package com.github.mvc.model.sps;

import lombok.Data;

import java.util.Date;

/**
 * @author 康盼Java开发工程师
 */
@Data
public class SchedulingPlanLotInfo {
    private String lotId;
    private int pq;
    private Date startDate;
    private Date StartTime;
    private Date finishDate;
    private Date finishTime;
    /**
     * 优先级
     */
    private String priorityClass;
    private int startPQ;
    private int finishPQ;
}
