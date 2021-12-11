package com.github.env.event;

import org.springframework.context.ApplicationEvent;

public class SyncToEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SyncToEvent(Object source) {
        super(source);
    }

}
