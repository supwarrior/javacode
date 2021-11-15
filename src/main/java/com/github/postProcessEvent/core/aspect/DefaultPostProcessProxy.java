package com.github.postProcessEvent.core.aspect;

import com.github.mycim.dto.Infos;
import com.github.postProcessEvent.core.manager.PostProcessPlanManager;
import com.github.postProcessEvent.model.PostProcessParam;
import com.github.postProcessEvent.model.PostProcessTask;
import com.github.postProcessEvent.model.PostProcessTaskPlan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Getter(AccessLevel.PROTECTED)
@Component("defaultProxy")
public class DefaultPostProcessProxy extends BasePostProcessProxy<Object, Object> {

    @Autowired
    public DefaultPostProcessProxy(PostProcessPlanManager planManager) {
        super(planManager);
    }

    @Override
    public List<PostProcessTask> plan(PostProcessParam.PlanTask<Object, Object> param) {

        Infos.ObjCommon objCommon = param.getObjCommon();
        PostProcessTaskPlan postProcessPlan = findTaskPlan(objCommon.getTransactionID());

        PostProcessParam.CreateTask createTask = new PostProcessParam.CreateTask();
        createTask.setObjCommon(objCommon);

        return postProcessPlan.generateTasks(createTask);
    }
}
