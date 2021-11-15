package com.github.postProcessEvent.core.executor;

import com.github.postProcessEvent.model.AvailablePhase;
import com.github.postProcessEvent.model.PostProcessTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PostProcessTaskHandler(available = AvailablePhase.ALL)
public class NonExecutor implements PostProcessExecutor {

    @Override
    public PostProcessTask.Result doExecute(PostProcessTask.Param param) {
        log.info("PostProcess Executor: no action...");
        return PostProcessTask.success();
    }
}
