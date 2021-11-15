package com.github.postProcessEvent.method;

import com.github.mycim.dto.Infos;
import com.github.postProcessEvent.model.PostProcessTask;

import java.util.List;

public interface IPostProcessTaskMethod {

    void createTaskRecords(Infos.ObjCommon objCommon, List<PostProcessTask> postProcessTask);
}
