package com.github.mycim.event;

import com.github.annotation.Core;
import com.github.annotation.Flush;

@Core
public class CimNonLotEdcEventBO extends AbstractEventBase<CimEventNonLotEdcDO> implements CimNonLotEdcEvent {

    public CimNonLotEdcEventBO(CimEventNonLotEdcDO entity) {
        super(entity);
    }

    @Flush(
            scope = Flush.Scope.ALL
    )
    @Override
    public void setEventData(Event.EventRecord eventRecord) {
        super.setEventData(eventRecord);
        CimEventNonLotEdcDO entity = this.getEntity();
        EdcEvent.NonLotEdcEventRecord record = (EdcEvent.NonLotEdcEventRecord)eventRecord;
        entity.setJoinObjectId(record.getJoinObjectId());
        entity.setJoinObjectRefKey(record.getJoinObjectRefKey());
        entity.setJoinObjectType(record.getJoinObjectType());
        entity.setEdcPlanId(record.getEdcPlanId());
        entity.setEdcPlanRefKey(record.getEdcPlanRefKey());
        entity.setEdcPlanDesc(record.getEdcPlanDesc());
        entity.setEdcSpecId(record.getEdcSpecId());
        entity.setEdcSpecRefKey(record.getEdcSpecRefKey());
        entity.setEdcSpecDesc(record.getEdcSpecDesc());
        entity.setReportTime(record.getReportTime());
        entity.setReportType(record.getReportType());
    }
}
