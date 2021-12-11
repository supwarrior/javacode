package com.github.env.event;

import org.springframework.context.ApplicationEvent;

public class SyncFromEvent extends ApplicationEvent {

    public SyncFromEvent(Object source) {
        super(source);
    }
}
