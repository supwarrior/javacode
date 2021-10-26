package com.github.code;

import com.github.annotation.Core;

/**
 * @author 康盼Java开发工程师
 */
@Core
public class CimEqpMonitorBO extends AbstractNamedEntity<CimEquipmentMonitorDO> implements CimEqpMonitor {

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public void makeDirty() {

    }

    @Override
    public boolean isDirty() {
        return false;
    }
}
