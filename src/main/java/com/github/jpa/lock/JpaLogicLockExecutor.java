package com.github.jpa.lock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.sql.Timestamp;

@Component
public class JpaLogicLockExecutor implements LogicLockExecutor {

    private final JpaLogicLockHandler lockHandler;
    private final EntityManager entityManager;

    @Autowired
    public JpaLogicLockExecutor(EntityManager entityManager, JpaLogicLockHandler lockHandler) {
        this.entityManager = entityManager;
        this.lockHandler = lockHandler;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void obtainLockForEntity(String objKey) {
        lockHandler.getAllLock(objKey).forEach((lock) -> {
            LockDO LockDO = entityManager.find(LockDO.class, lock.getId());
            // 其他事务不能同时读取或写入实体
            entityManager.lock(LockDO, LockModeType.PESSIMISTIC_WRITE);
            if (StringUtils.equals(LockDO.getAttributeKey(), "ALL")) {
                LockDO.setLastLockedTime(new Timestamp(System.currentTimeMillis()));
            }
        });
    }

    @Override
    public void obtainLockForAttribute(String objKey, String attKey) {

    }

    @Override
    public void releaseLockForEntity(String objKey) {

    }

    @Override
    public void releaseLockForAttribute(String objKey, String attKey) {

    }
}
