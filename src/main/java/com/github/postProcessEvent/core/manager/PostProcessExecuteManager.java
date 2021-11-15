package com.github.postProcessEvent.core.manager;

import com.github.postProcessEvent.model.PostProcessParam;

public interface PostProcessExecuteManager {

    void executeChained(PostProcessParam.Execute param);
}
