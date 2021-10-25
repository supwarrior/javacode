package com.github.code;

import com.github.annotation.CoreBeanCache;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 康盼Java开发工程师
 */
@CoreBeanCache
@Component
public class CimJpaRepository {

    @PersistenceContext
    protected EntityManager em;

    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public <T extends BaseCimEntity> T save(T entity) {
        if (StringUtils.isEmpty(entity)) {
            // entity.setId(SnowflakeIDWorker.getInstance().generateId(entity.getClass()));
            entity.setId(1L);
        }
        if (this.em.find(entity.getClass(), entity.getId()) != null) {
            this.em.merge(entity);
        } else {
            this.em.persist(entity);
        }

        return entity;
    }
}
