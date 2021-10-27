package com.github.ddd;

import com.github.ddd.domainObject.BaseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class JpaRepository {

    @PersistenceContext
    protected EntityManager em;

    @Transactional(rollbackFor = Exception.class)
    public <T extends BaseEntity> T save(T entity) {
        if (StringUtils.isEmpty(entity.getId()) || entity.getId().equals("0")) {
            entity.setId(SnowflakeIDWorker.getInstance().generateId(entity.getClass()));
        }
        if (this.em.find(entity.getClass(), entity.getId()) != null) {
            this.em.merge(entity);
        } else {
            this.em.persist(entity);
        }
        return entity;
    }
}