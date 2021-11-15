package com.github.postProcessEvent.core.manager;

import com.github.postProcessEvent.model.PostProcessTaskPlan;

public interface PostProcessPlanManager {

    PostProcessTaskPlan findPostProcessPlan(String transactionId);

}
