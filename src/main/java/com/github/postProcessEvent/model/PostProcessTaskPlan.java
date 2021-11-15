package com.github.postProcessEvent.model;

import com.github.esec.core.SnowflakeIDWorker;
import com.github.mycim.dto.Infos;
import com.github.postProcessEvent.core.executor.PostProcessExecutor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 后处理任务计划
 */
@Getter
public class PostProcessTaskPlan {


    private final String patternId;
    private final String transactionId;
    private final List<Definition> definitions = new LinkedList<>();

    public PostProcessTaskPlan(String patternId, String transactionId) {
        this.patternId = patternId;
        this.transactionId = transactionId;
    }

    public static void clearTaskId() {
        LOCAL_TASK_ID.remove();
    }

    /**
     * task 定义
     */
    @Getter
    @Setter
    public static class Definition {
        private String executorId;
        private PostProcessExecutor executor;
        private boolean isNextOperationRequired;
        private boolean chained;
        private JoinMode joinMode;
    }

    private static final ThreadLocal<String> LOCAL_TASK_ID = new ThreadLocal<>();

    public static void generateTaskId(String userId) {
        LOCAL_TASK_ID.set(String.format("%s++%s", SnowflakeIDWorker.getInstance().nextID(), userId));
    }

    public List<PostProcessTask> generateTasks(PostProcessParam.CreateTask param) {
        List<PostProcessTaskPlan.Definition> sectioned = this.getDefinitions();
        Infos.ObjCommon objCommon = param.getObjCommon();
        AtomicBoolean chainToMain = new AtomicBoolean(true);
        List<PostProcessTask> tasks = sectioned.stream().map(taskDefinition -> {
                    PostProcessTask task = new PostProcessTask();
                    task.setTaskId(LOCAL_TASK_ID.get());
                    task.setObjCommon(objCommon);
                    task.setDefinition(taskDefinition);
                    return task;
                }).peek(task -> ExecutePhase.getPhase(task, chainToMain))
                .collect(Collectors.toList());

        return tasks;
    }

}
