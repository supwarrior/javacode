package com.github.mycim.service.access;

import com.github.mycim.common.support.User;
import com.github.mycim.dto.Infos;
import com.github.mycim.dto.Params;

public interface IAccessInqService {

    Infos.ObjCommon checkPrivilegeAndGetObjCommon(String transactionID, User user, Params.AccessControlCheckInqParams accessControlCheckInqParams);
}
