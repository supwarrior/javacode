package com.github.postProcessEvent.core.aspect;

import com.github.postProcessEvent.model.PostProcessParam;
import com.github.postProcessEvent.model.PostProcessTask;

import java.util.List;

public interface PostProcessPlanProxy<P, R> {
    List<PostProcessTask> plan(PostProcessParam.PlanTask<P, R> param);
}
