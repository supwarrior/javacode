package com.github.postProcessEvent.core.manager;

import com.github.ddd.SpringContextUtil;
import com.github.postProcessEvent.core.executor.PostProcessExecutor;
import com.github.postProcessEvent.core.executor.PostProcessTaskHandler;
import com.github.postProcessEvent.dao.PostProcessPatternDefinitionDao;
import com.github.postProcessEvent.entity.CimPostProcessPatternDefinitionDO;
import com.github.postProcessEvent.model.JoinMode;
import com.github.postProcessEvent.model.PostProcessTaskPlan;
import com.github.postProcessEvent.model.TaskExecutors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostProcessPlanManagerImpl implements PostProcessPlanManager {

    private final PostProcessPatternDefinitionDao patternDefinitionDao;

    @Autowired
    public PostProcessPlanManagerImpl(PostProcessPatternDefinitionDao patternDefinitionDao) {
        this.patternDefinitionDao = patternDefinitionDao;
    }

    @Override
    public PostProcessTaskPlan findPostProcessPlan(String transactionId) {
        // List<CimPostProcessPatternDefinitionDO> dataList = patternDefinitionDao.findAllByTransactionId(transactionId);

        // 这里模拟数据
        List<CimPostProcessPatternDefinitionDO> dataList = new ArrayList<>();
        CimPostProcessPatternDefinitionDO cimPostProcessPatternDefinitionDO = new CimPostProcessPatternDefinitionDO();
        cimPostProcessPatternDefinitionDO.setExecutorId("NonExecutor");
        cimPostProcessPatternDefinitionDO.setPatternId("0");
        cimPostProcessPatternDefinitionDO.setJoinMode("NORMAL");
        dataList.add(cimPostProcessPatternDefinitionDO);


        String patternId = dataList.stream().map(CimPostProcessPatternDefinitionDO::getPatternId)
                .findAny().orElseThrow(() -> new RuntimeException("Not Found Pattern ID - shouldn't have reached here"));
        PostProcessTaskPlan plan = new PostProcessTaskPlan(patternId, transactionId);

        List<PostProcessTaskPlan.Definition> definitions = dataList.stream().map(data -> {
            PostProcessTaskPlan.Definition definition = new PostProcessTaskPlan.Definition();
            definition.setJoinMode(JoinMode.valueOf(data.getJoinMode()));
            TaskExecutors taskExecutor = TaskExecutors.valueOf(data.getExecutorId());
            definition.setExecutorId(taskExecutor.name());
            // 根据 executorId 实例化
            Class<? extends PostProcessExecutor> executorType = taskExecutor.getExecutorType();
            definition.setExecutor(SpringContextUtil.getSingletonBean(executorType));
            definition.setNextOperationRequired(executorType.getAnnotation(PostProcessTaskHandler.class).isNextOperationRequired());
            return definition;
        }).collect(Collectors.toList());
        plan.getDefinitions().addAll(definitions);
        return plan;
    }

}
