package com.github.analysis;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 定义事件
 */
@Setter
@Getter
@Slf4j
public class PostProcessEvent extends ApplicationEvent {

    private Event event;

    public PostProcessEvent(Event event) {
        super(event);
        this.event = event;
    }

    @Data
    @ToString
    public static class Event {
        private String taskId;
        private String description;
    }

    /**
     * 监听事件
     */
    @Component("listenerAnalysis")
    public static class Listener {
        @EventListener
        public void postProcessEvent(PostProcessEvent postProcessEvent) {
            log.info("log:{}",postProcessEvent.getEvent());
        }
    }
}
