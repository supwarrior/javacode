package com.github.mycim.event;

import com.github.ddd.businessObject.BaseBO;

public interface EventManager extends BaseBO {

    <T extends CimEventBase> CimEventBase createEvent(Event.EventRecord eventRecord, Class<T> eventType);
}
