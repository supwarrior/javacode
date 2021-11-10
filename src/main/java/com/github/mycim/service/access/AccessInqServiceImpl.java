package com.github.mycim.service.access;

import com.github.mycim.common.support.User;
import com.github.mycim.dto.Infos;
import com.github.mycim.dto.Params;
import com.github.mycim.dto.TaskContextHolder;
import com.github.mycim.method.person.IPersonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AccessInqServiceImpl implements IAccessInqService {

    @Autowired
    private IPersonMethod personMethod;

    @Override
    public Infos.ObjCommon checkPrivilegeAndGetObjCommon(String transactionID, User user, Params.AccessControlCheckInqParams accessControlCheckInqParams) {
        Infos.ObjCommon objCommon = setObjCommon(transactionID, user);
        this.sxAccessControlCheckInq(objCommon, accessControlCheckInqParams);
        TaskContextHolder.setObjCommon(objCommon);
        return objCommon;
    }

    public Infos.ObjCommon setObjCommon(String transactionID, User user) {
        Infos.ObjCommon objCommon = getPPTObjCommonInInstance(transactionID, user);
        return objCommon;
    }

    private Infos.ObjCommon getPPTObjCommonInInstance(String transactionID, User requestUserID) {
        Infos.ObjCommon objCommon = new Infos.ObjCommon();
        objCommon.setTransactionID(transactionID);
        objCommon.setUser(requestUserID);
        Infos.TimeStamp timeStamp = new Infos.TimeStamp();
        timeStamp.setReportTimeStamp(new Timestamp(System.currentTimeMillis()));
        objCommon.setTimeStamp(timeStamp);
        return objCommon;
    }

    public void sxAccessControlCheckInq(Infos.ObjCommon objCommon, Params.AccessControlCheckInqParams params) {
        personMethod.personPrivilegeCheckDR(objCommon, objCommon.getUser(), params.getEquipmentID(), params.getStockerID(), params.getProductIDList(), params.getRouteIDList(), params.getLotIDLists(), params.getMachineRecipeIDList());
        this.departmentAndSectionCheckInq(objCommon, params);
        TaskContextHolder.setObjCommon(objCommon);
    }

    private void departmentAndSectionCheckInq(Infos.ObjCommon objCommon, Params.AccessControlCheckInqParams params) {
    }
}
