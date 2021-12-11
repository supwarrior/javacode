package com.github.env.core;

import com.github.ddd.SpringContextUtil;
import com.github.env.app.dao.EnvironmentVariableDao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DBSource extends AbstractSwitchPropertySource {

    @Override
    public List<EnvironmentVariableDO> allFromSource() {
        List<EnvironmentVariableDO> all = SpringContextUtil.getSingletonBean(EnvironmentVariableDao.class).findAll();
        return all.isEmpty() ? Collections.emptyList() : all;
    }

    /**
     * 直接入库
     *
     * @param variable
     */
    @Override
    public void refreshSource(EnvironmentVariable variable) {
        EnvironmentVariableDao dao = SpringContextUtil.getSingletonBean(EnvironmentVariableDao.class);
        EnvironmentVariableDO environmentVariableDO = Optional.ofNullable(dao.findByEnvironmentVariableID(variable.getName()))
                .orElseGet(() -> {
                    EnvironmentVariableDO data = new EnvironmentVariableDO();
                    data.setEnvironmentVariableID(variable.getName());
                    return data;
                });
        environmentVariableDO.setEnvironmentVariableValue(variable.getValue());
        dao.save(environmentVariableDO);
    }
}
