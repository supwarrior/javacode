package com.github.postProcessEvent.core.event;

import com.github.mycim.dto.Infos;
import com.github.postProcessEvent.core.manager.PostProcessExecuteManager;
import com.github.postProcessEvent.model.DispatchReadinessState;
import com.github.postProcessEvent.model.ExecutePhase;
import com.github.postProcessEvent.model.PostProcessParam;
import com.github.postProcessEvent.model.PostProcessTask;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * 事件发布
 */
@Getter
@Setter
@Slf4j
public abstract class PostProcessEvent extends ApplicationEvent {

    private List<PostProcessTask> tasks;
    private Infos.ObjCommon objCommon;
    private String taskId;

    public PostProcessEvent(Object source) {
        super(source);
    }

    public PostProcessEvent(PostProcessEvent event) {
        super(event.source);
        this.tasks = event.tasks;
        this.objCommon = event.objCommon;
        this.taskId = event.taskId;
    }

    public static PostProcessEvent syncEvent(Object source, ExecutePhase phase) {
        return new SyncEvent(source, phase);
    }

    public static abstract class ProcessingEvent extends PostProcessEvent {

        private final ExecutePhase phase;

        private ProcessingEvent(Object source, ExecutePhase phase) {
            super(source);
            this.phase = phase;
        }

        public PostProcessEvent newPhasedEvent() {
            if (phase == ExecutePhase.CHAINED) {
                return new ChainedEvent(this);
            } else {
                return new PostEvent(this);
            }
        }
    }

    /**
     * 事件类型
     */
    public static class SyncEvent extends ProcessingEvent {
        private SyncEvent(Object source, ExecutePhase phase) {
            super(source, phase);
        }
    }

    public static class ChainedEvent extends PostProcessEvent {
        public ChainedEvent(PostProcessEvent event) {
            super(event);
        }
    }

    public static class JoinedEvent extends PostProcessEvent {
        public JoinedEvent(PostProcessEvent event) {
            super(event);
        }
    }

    public static class PostEvent extends JoinedEvent {
        private PostEvent(PostProcessEvent event) {
            super(event);
        }
    }

    /**
     * 事件监听
     */
    @Component
    public static class Listener {

        @Autowired
        private PostProcessExecuteManager executeManager;


        private  ApplicationEventPublisher eventPublisher;

        @EventListener
        public void syncEventProcessor(SyncEvent event) {
            log.info("Synchronously execute Post Process Task start ...");
           // eventPublisher.publishEvent(event.newPhasedEvent()); 这里可以转发其它事件监听处理
            PostProcessParam.Execute chainedParam = new PostProcessParam.Execute();
            chainedParam.setTaskId(event.getTaskId());
            chainedParam.setObjCommon(event.getObjCommon());
            chainedParam.setPhase(ExecutePhase.CHAINED);
            chainedParam.setMainSuccessful(true);

            List<PostProcessTask> tasks = event.getTasks();
            chainedParam.setTasks(tasks);
            DispatchReadinessState nextState;

            if (tasks.stream().anyMatch(task -> task.getPhase() == ExecutePhase.JOINED)) {
                nextState = DispatchReadinessState.Pending_JoinedTasks;
            } else if (tasks.stream().anyMatch(task -> task.getPhase() == ExecutePhase.POST)) {
                nextState = DispatchReadinessState.Pending_PostTasks;
            } else {
                nextState = DispatchReadinessState.Ready;
            }
            chainedParam.setNextStatus(nextState);
            // 执行
            executeManager.executeChained(chainedParam);
            log.info("Synchronously execute Post Process Task end ...");
        }
        @EventListener
        @Order(10)
        public void joinedEventProcessor(JoinedEvent event) {

        }

        @EventListener
        @Order(50)
        public void postEventProcessor(PostEvent event) {
        }

        @EventListener
        public void chainedEventProcessor(ChainedEvent event) {
        }


    }


}
