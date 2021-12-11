package com.github.env.app.dao;

import com.github.env.core.EnvironmentVariableDO;
import com.github.jpa.lock.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentVariableDao extends BaseDao<EnvironmentVariableDO> {
    EnvironmentVariableDO findByEnvironmentVariableID(String environmentVariableID);
}
