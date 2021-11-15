package com.github.postProcessEvent.method;

import com.github.ddd.jpa.JpaRepository;
import com.github.esec.core.SnowflakeIDWorker;
import com.github.mycim.dto.Infos;
import com.github.postProcessEvent.core.OmMethod;
import com.github.postProcessEvent.dao.PostProcessTaskDao;
import com.github.postProcessEvent.dao.PostProcessTaskDetailDao;
import com.github.postProcessEvent.entity.CimPostProcessTaskDO;
import com.github.postProcessEvent.entity.CimPostProcessTaskDetailDO;
import com.github.postProcessEvent.model.JoinMode;
import com.github.postProcessEvent.model.PostProcessTask;
import com.github.postProcessEvent.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@OmMethod
public class PostProcessTaskMethod implements IPostProcessTaskMethod {

    private final PostProcessTaskDao taskDao;
    private final PostProcessTaskDetailDao taskDetailDao;

    @Autowired
    private JpaRepository jpaRepository;

    @Autowired
    public PostProcessTaskMethod(PostProcessTaskDao taskDao,
                                 PostProcessTaskDetailDao taskDetailDao) {
        this.taskDao = taskDao;
        this.taskDetailDao = taskDetailDao;
    }

    @Override
    public void createTaskRecords(Infos.ObjCommon objCommon, List<PostProcessTask> postProcessTask) {

        AtomicInteger taskCounter = new AtomicInteger();
        List<CimPostProcessTaskDO> taskDataList = new ArrayList<>();
        List<CimPostProcessTaskDetailDO> detailDataList = new ArrayList<>();
        postProcessTask.stream().forEach(task -> {
            CimPostProcessTaskDO cimPostProcessTaskDO = new CimPostProcessTaskDO();
            cimPostProcessTaskDO.setTaskId(task.getTaskId());
            cimPostProcessTaskDO.setTransactionId(task.getObjCommon().getTransactionID());
            cimPostProcessTaskDO.setTaskStatus(TaskStatus.Reserved.name());
            cimPostProcessTaskDO.setJoinMode(JoinMode.JOINED.toString());
            cimPostProcessTaskDO.setTrxUserId(objCommon.getUser().getUserID().getReferenceKey());
            cimPostProcessTaskDO.setId(SnowflakeIDWorker.getInstance().generateId(CimPostProcessTaskDO.class));
            cimPostProcessTaskDO.setTrxTime(objCommon.getTimeStamp().getReportTimeStamp());
            cimPostProcessTaskDO.setIndexNumber(taskCounter.getAndIncrement());
            cimPostProcessTaskDO.setCreateTime(objCommon.getTimeStamp().getReportTimeStamp());
            cimPostProcessTaskDO.setUpdateTime(objCommon.getTimeStamp().getReportTimeStamp());
            taskDataList.add(cimPostProcessTaskDO);
            AtomicInteger detailCounter = new AtomicInteger(0);
            task.getDetails().forEach(detail -> {
                CimPostProcessTaskDetailDO cimPostProcessTaskDetailDO = new CimPostProcessTaskDetailDO();
                cimPostProcessTaskDetailDO.setRefKey(cimPostProcessTaskDO.getId());
                cimPostProcessTaskDetailDO.setName(detail.getName());
                cimPostProcessTaskDetailDO.setValue(detail.getValue());
                cimPostProcessTaskDetailDO.setIndexNo(detailCounter.getAndIncrement());
                detailDataList.add(cimPostProcessTaskDetailDO);
            });
        });

        this.taskDao.saveAll(taskDataList);
        this.taskDetailDao.saveAll(detailDataList);
    }
}
