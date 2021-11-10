package com.github.mycim.method.person;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.common.support.User;
import com.github.mycim.dto.Infos;

import java.util.List;

public interface IPersonMethod {
    void personPrivilegeCheckDR(Infos.ObjCommon objCommon, User user, ObjectIdentifier equipmentID, ObjectIdentifier stockerID, List<ObjectIdentifier> productIDList, List<ObjectIdentifier> routeIDList, List<ObjectIdentifier> lotIDLists, List<ObjectIdentifier> machineRecipeIDList);
}
