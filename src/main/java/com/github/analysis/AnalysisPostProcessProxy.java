package com.github.analysis;

import org.springframework.stereotype.Component;

/**
 * 自定义扩展
 *
 * @param <P>
 * @param <R>
 */
@Component
public class AnalysisPostProcessProxy<P, R> extends BasePostProcessProxy<P, R> {
    @Override
    public PostProcessEvent getPostProcessEvent(PlanTask<P, R> param) {
        PostProcessEvent postProcessEvent = super.getPostProcessEvent(param);
        postProcessEvent.getEvent().setTaskId("AnalysisPostProcessProxy");
        return postProcessEvent;
    }
}
