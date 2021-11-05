package com.github.analysis;

public abstract class BasePostProcessProxy<P, R> implements PostProcessPlanProxy<P, R> {

    @Override
    public PostProcessEvent getPostProcessEvent(PlanTask<P, R> param) {
        StringBuilder stringBuilder = new StringBuilder("arguments: [");
        stringBuilder.append(param.getArguments().toString()).append("], result:[");
        stringBuilder.append(param.getResult().toString()).append("]");
        PostProcessEvent.Event event = new PostProcessEvent.Event();
        event.setDescription(stringBuilder.toString());
        PostProcessEvent postProcessEvent = new PostProcessEvent(event);
        return postProcessEvent;
    }
}
