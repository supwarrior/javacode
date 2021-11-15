package com.github.postProcessEvent.model;

import com.github.postProcessEvent.core.executor.PostProcessExecutor;
import com.github.postProcessEvent.core.executor.PostProcessTaskHandler;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 执行阶段
 */
public enum ExecutePhase {

    /**
     * execute the task in chain phase
     */
    CHAINED,

    /**
     * execute the task in joined phase
     */
    JOINED,

    /**
     * execute the task in post phase
     */
    POST;

    public static ExecutePhase getPhase(PostProcessTask task, AtomicBoolean chainedToMain) {
        JoinMode joinMode = task.getDefinition().getJoinMode();
        ExecutePhase phase = joinMode != JoinMode.JOINED ? POST : (true ? CHAINED : JOINED);
        PostProcessExecutor executor = task.getDefinition().getExecutor();
        return Optional.ofNullable(executor.getClass().getAnnotation(PostProcessTaskHandler.class))
                .map(PostProcessTaskHandler::available).orElse(AvailablePhase.ALL)
                .override(phase, true);
    }
}
