package com.github.mycim.method.event;

import com.github.mycim.dto.Infos;
import com.github.mycim.model.eqp.params.NonLotDataCollectionEventParams;

public interface IEventMethod {

    void nonLotDataCollectionEventMake(Infos.ObjCommon objCommon,
                                       NonLotDataCollectionEventParams nonLotDataCollectionEventParams);
}
