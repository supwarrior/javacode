package com.github.postProcessEvent.core.aspect;

import com.github.postProcessEvent.core.manager.PostProcessPlanManager;
import com.github.postProcessEvent.model.PostProcessTaskPlan;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter(AccessLevel.PROTECTED)
public abstract class BasePostProcessProxy<P, R> implements PostProcessPlanProxy<P, R> {

    private final PostProcessPlanManager planManager;

    @Autowired
    public BasePostProcessProxy(PostProcessPlanManager planManager) {
        this.planManager = planManager;
    }

    protected PostProcessTaskPlan findTaskPlan(String transactionId) {
        return planManager.findPostProcessPlan(transactionId);
    }

}
