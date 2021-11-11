package com.github.mycim.method.person;

import com.github.jpa.lock.ObjectIdentifier;
import com.github.mycim.common.support.User;
import com.github.mycim.dto.Infos;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMethod implements IPersonMethod {

    /**
     * 用户权限检查 检查密码是否正确 长度 过期时间
     *
     * @param objCommon
     * @param user
     * @param equipmentID
     * @param stockerID
     * @param productIDList
     * @param routeIDList
     * @param lotIDLists
     * @param machineRecipeIDList
     */
    @Override
    public void personPrivilegeCheckDR(Infos.ObjCommon objCommon, User user, ObjectIdentifier equipmentID, ObjectIdentifier stockerID, List<ObjectIdentifier> productIDList, List<ObjectIdentifier> routeIDList, List<ObjectIdentifier> lotIDLists, List<ObjectIdentifier> machineRecipeIDList) {

    }
}
