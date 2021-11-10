package com.github.mycim.event;

import com.github.annotation.Flush;
import com.github.ddd.businessObject.AbstractBO;

public abstract class AbstractEventBase<T extends EventBaseDO> extends AbstractBO<T> implements CimEventBase {


    public AbstractEventBase(T entity) {
        super(entity);
    }

    @Flush
    public void setEventData(Event.EventRecord eventRecord) {
        T entity = this.getEntity();
        Event.EventData eventCommon = eventRecord.getEventCommon();
        entity.setTxId(eventCommon.getTransactionID());
        entity.setClaimMemo(eventCommon.getEventMemo());
        entity.setClaimUserId(eventCommon.getUserID());
        entity.setEventTime(eventCommon.getEventTimeStamp());
    }

    @Flush(
            scope = Flush.Scope.ATTRIBUTE
    )
    public void addFIFOData(String name, String eventTime) {


    }
}
