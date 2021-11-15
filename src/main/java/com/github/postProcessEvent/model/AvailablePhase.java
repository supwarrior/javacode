package com.github.postProcessEvent.model;

import java.util.function.BiFunction;

public enum AvailablePhase {

    ALL((phase, chainedFlag) -> phase);

    private final BiFunction<ExecutePhase, Boolean, ExecutePhase> function;

    AvailablePhase(BiFunction<ExecutePhase, Boolean, ExecutePhase> function) {
        this.function = function;
    }

    public ExecutePhase override(ExecutePhase phase, boolean chained) {
        return this.function.apply(phase, chained);
    }
}
