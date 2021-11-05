package com.github.analysis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public interface PostProcessPlanProxy<P, R> {

    public PostProcessEvent getPostProcessEvent(PlanTask<P , R> param);

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class PlanTask<P, R> {
        private final P arguments;
        private final R result;
    }
}
