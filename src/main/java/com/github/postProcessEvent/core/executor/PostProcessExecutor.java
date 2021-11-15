package com.github.postProcessEvent.core.executor;

import com.github.postProcessEvent.model.PostProcessTask;

/**
 * 后置处理器
 */
public interface PostProcessExecutor {
    PostProcessTask.Result doExecute(PostProcessTask.Param param);
}
