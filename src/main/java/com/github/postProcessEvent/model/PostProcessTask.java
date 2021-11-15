package com.github.postProcessEvent.model;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.dto.Infos;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostProcessTask {

    private String taskId;
    private Infos.ObjCommon objCommon;
    private ObjectIdentifier entityID;
    private ObjectIdentifier equipmentID;
    private ObjectIdentifier controlJobID;
    private Timestamp createTime;
    private Timestamp trxTime;
    private ObjectIdentifier trxUserID;
    private List<Detail> details = new ArrayList<>();
    private ExecutePhase phase;

    private PostProcessTaskPlan.Definition definition;

    @Getter
    public static class Detail {
        private final String name;
        private final String value;

        public Detail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    @Getter
    @Setter
    public static class Result {

        private final boolean success;
        private final boolean releaseHold;
        private final Object body;

        public Result(boolean success, boolean releaseHold) {
            this.success = success;
            this.releaseHold = releaseHold;
            this.body = null;
        }

        public Result(boolean success, boolean releaseHold, Object body) {
            this.success = success;
            this.releaseHold = releaseHold;
            this.body = body;
        }

        public Result(boolean success, Object body) {
            this.success = success;
            this.body = body;
            this.releaseHold = true;
        }

        public Result(boolean success) {
            this.success = success;
            this.releaseHold = true;
            this.body = null;
        }

    }

    @ToString
    @Getter
    public static class Param {
        private final String taskId;
        private final Infos.ObjCommon objCommon;
        private final ObjectIdentifier entityID;
        private final ObjectIdentifier equipmentID;
        private final ObjectIdentifier controlJobID;

        private Param(PostProcessTask task) {
            this.taskId = task.taskId;
            this.objCommon = task.objCommon;
            this.entityID = task.entityID;
            this.equipmentID = task.equipmentID;
            this.controlJobID = task.controlJobID;
        }

    }

    public static Result success() {
        return new PostProcessTask.Result(true);
    }

    public Result execute() {
        return this.getDefinition().getExecutor().doExecute(new Param(this));
    }


}
