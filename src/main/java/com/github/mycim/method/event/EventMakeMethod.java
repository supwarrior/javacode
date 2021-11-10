package com.github.mycim.method.event;

import com.github.mycim.dto.Infos;
import com.github.mycim.event.CimNonLotEdcEvent;
import com.github.mycim.event.EdcEvent;
import com.github.mycim.event.Event;
import com.github.mycim.event.EventManager;
import com.github.mycim.model.eqp.params.NonLotDataCollectionEventParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EventMakeMethod implements IEventMethod {

    @Autowired
    private EventManager eventManager;

    @Override
    public void nonLotDataCollectionEventMake(Infos.ObjCommon objCommon, NonLotDataCollectionEventParams nonLotDataCollectionEventParams) {
        EdcEvent.NonLotEdcEventRecord nonLotEdcEventRecord = new EdcEvent.NonLotEdcEventRecord();
        BeanUtils.copyProperties(nonLotDataCollectionEventParams, nonLotEdcEventRecord);
        nonLotEdcEventRecord.setEventCommon(setEventData(objCommon, ""));

        eventManager.createEvent(nonLotEdcEventRecord, CimNonLotEdcEvent.class);
    }





    private Event.EventData setEventData(Infos.ObjCommon objCommon, String claimMemo) {
        Event.EventData eventData = new Event.EventData();
        eventData.setTransactionID(objCommon.getTransactionID());
        eventData.setEventTimeStamp(objCommon.getTimeStamp().getReportTimeStamp().toString());
        eventData.setEventShopDate(objCommon.getTimeStamp().getReportShopDate());
        eventData.setUserID(objCommon.getUser().getUserID().getValue());
        eventData.setEventMemo(claimMemo);
        eventData.setEventCreationTimeStamp(getCurrentDateTimeWithDefault());
        return eventData;
    }

    private static final DateTimeFormatter DEFAULT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getCurrentDateTimeWithDefault() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DEFAULT);
    }

}
