package com.github.mycim.event;

import com.github.annotation.CoreManager;

import java.util.Iterator;
import java.util.List;

@CoreManager("EventManagerCore")
public class EventManagerBO  extends AbstractManager implements EventManager {

    @Override
    public <T extends CimEventBase> CimEventBase createEvent(Event.EventRecord eventRecord, Class<T> eventType) {

        Event.EventData eventCommon = eventRecord.getEventCommon();
        String txID = eventCommon.getTransactionID();
        String eventTime = eventCommon.getEventTimeStamp();

        String sql = "SELECT OMRESPSENTINEL.* FROM OMRESPSENTINEL WHERE TRX_ID = ?";
        List<CimWatchDogDO> watchDogDOS = this.coreJpaRepository.query(sql, CimWatchDogDO.class, new Object[]{txID});
        CimEventBase retVal = this.genericCoreFactory.newBO(eventType);
        retVal.setEventData(eventRecord);

        Iterator iterator = watchDogDOS.iterator();
        while(iterator.hasNext()) {
            CimWatchDogDO watchDog = (CimWatchDogDO)iterator.next();
            String watchDogName = watchDog.getWatchDogName();
            retVal.addFIFOData(watchDogName, eventTime);
        }
        return null;
    }
}
