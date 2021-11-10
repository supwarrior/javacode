package com.github.mycim.service.constraint;

import com.github.mycim.dto.Infos;

public interface IConstraintService {
    Infos.ConstraintEqpDetailInfo sxConstraintEqpAddReq(
            Infos.ObjCommon objCommon, Infos.ConstraintDetailAttributes entityInhibitDetailAttributes, String claimMemo);
}
