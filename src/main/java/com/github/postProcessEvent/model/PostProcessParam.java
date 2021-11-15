package com.github.postProcessEvent.model;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.dto.Infos;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public abstract class PostProcessParam {

    private String taskId;

    @Getter
    @Setter
    @ToString
    public static class PlanTask<P, R> {
        private final Infos.ObjCommon objCommon;
        private final P arguments;
        private final R result;

        public PlanTask(Infos.ObjCommon objCommon, P arguments, R result) {
            this.objCommon = objCommon;
            this.arguments = arguments;
            this.result = result;
        }
    }

    @Getter
    @Setter
    public static class CreateTask {
        private Infos.ObjCommon objCommon;
        private List<ObjectIdentifier> lotID = Collections.emptyList();
        private List<ObjectIdentifier> durableID = Collections.emptyList();
        private ObjectIdentifier equipmentID = ObjectIdentifier.emptyIdentifier();
        private ObjectIdentifier controlJobID = ObjectIdentifier.emptyIdentifier();
    }

    @Getter
    @Setter
    public static class ExecuteJoinTasks extends PostProcessParam {
        private String transactionId;
    }


    @Getter
    @Setter
    public static class Register extends PostProcessParam {
        private List<PostProcessTask> postTasks;
    }

    @Getter
    @Setter
    public static class Execute extends PostProcessParam {
        private Infos.ObjCommon objCommon;
        private List<PostProcessTask> tasks;
        private boolean mainSuccessful;
        private DispatchReadinessState nextStatus;
        private ExecutePhase phase;
    }
}
