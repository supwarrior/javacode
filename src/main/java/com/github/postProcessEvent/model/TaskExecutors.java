package com.github.postProcessEvent.model;

import com.github.postProcessEvent.core.executor.NonExecutor;
import com.github.postProcessEvent.core.executor.PostProcessExecutor;
import lombok.Getter;

public enum TaskExecutors {

    NonExecutor(NonExecutor.class);

    @Getter
    private final Class<? extends PostProcessExecutor> executorType;

    TaskExecutors(Class<? extends PostProcessExecutor> executorType) {
        this.executorType = executorType;
    }
}
