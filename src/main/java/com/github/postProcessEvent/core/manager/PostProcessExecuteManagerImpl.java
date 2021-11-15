package com.github.postProcessEvent.core.manager;

import com.github.postProcessEvent.model.PostProcessParam;
import com.github.postProcessEvent.model.PostProcessTask;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PostProcessExecuteManagerImpl implements PostProcessExecuteManager {


    @Override
    public void executeChained(PostProcessParam.Execute param) {
        new TaskExecuteAction(param).execute();
    }

    @Getter
    class TaskExecuteAction {

        private final PostProcessParam.Execute param;

        private TaskExecuteAction(PostProcessParam.Execute param) {
            this.param = param;
        }

        public void execute() {
            List<PostProcessTask> tasks = (new LinkedList<>(param.getTasks()));
            while (!tasks.isEmpty()) {
                PostProcessTask task = tasks.remove(0);
                PostProcessTask.Result result = task.execute();
            }
        }
    }
}
