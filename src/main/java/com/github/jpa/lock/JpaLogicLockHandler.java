package com.github.jpa.lock;

import com.github.ddd.SnowflakeIDWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class JpaLogicLockHandler {

    private final EntityManager entityManager;
    private final LockDao lockDao;

    @Autowired
    public JpaLogicLockHandler(EntityManager entityManager, LockDao lockDao) {
        this.entityManager = entityManager;
        this.lockDao = lockDao;
    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void tryAllLock(String objectKey) {
        this.lockDao.findByObjectKeyAndAttributeKey(objectKey, "ALL").ifPresent((entity) -> {
            this.entityManager.lock(entity, LockModeType.PESSIMISTIC_WRITE);
        });
    }

    private LockDO newLockEntity(String objectKey, String attributeKey) {
        LockDO LockDO = new LockDO();
        LockDO.setLockCreateTime(new Timestamp(System.currentTimeMillis()));
        LockDO.setObjectKey(objectKey);
        LockDO.setAttributeKey(attributeKey);
        LockDO.setId(SnowflakeIDWorker.getInstance().generateId(LockDO.class));
        return LockDO;
    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public List<LockDO> getAllLock(String objectKey) {
        List<LockDO> locks = lockDao.findAllByObjectKeyOrderByAttributeKey(objectKey);
        AtomicReference<LockDO> allLock = new AtomicReference();
        boolean isNotPresent = !locks.removeIf((lock) -> {
            boolean equals = StringUtils.equals(lock.getAttributeKey(), "ALL");
            if (equals) {
                allLock.set(lock);
            }
            return equals;
        });

        if (isNotPresent) {
            allLock.set(newLockEntity(objectKey, "ALL"));
            try {
                entityManager.persist(allLock.get());
            } catch (DuplicateKeyException duplicateKeyException) {
                return getAllLock(objectKey);
            }
        }
        List<LockDO> retVal = new ArrayList(locks.size() + 1);
        retVal.add(allLock.get());
        retVal.addAll(locks);
        return retVal;
    }
}
