package com.github.mycim.event;

import com.github.ddd.businessObject.BaseBO;

public interface CimEventBase extends BaseBO {

    void setEventData(Event.EventRecord eventRecord);

    void addFIFOData(String name, String eventTime);
}
