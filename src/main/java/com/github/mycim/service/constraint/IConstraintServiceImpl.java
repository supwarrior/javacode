package com.github.mycim.service.constraint;

import com.github.mycim.dto.Infos;
import org.springframework.stereotype.Service;

@Service
public class IConstraintServiceImpl implements IConstraintService {

    @Override
    public Infos.ConstraintEqpDetailInfo sxConstraintEqpAddReq(
            Infos.ObjCommon objCommon,
            Infos.ConstraintDetailAttributes entityInhibitDetailAttributes, String claimMemo) {
        return null;
    }
}
