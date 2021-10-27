package com.github.ddd;

import com.github.ddd.domainObject.BaseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class JpaRepository {

    @PersistenceContext
    protected EntityManager em;

    @Transactional(rollbackFor = Exception.class)
    public <T extends BaseEntity> T save(T entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(SnowflakeIDWorker.getInstance().generateId(entity.getClass()));
        }
        if (this.em.find(entity.getClass(), entity.getId()) != null) {
            this.em.merge(entity);
        } else {
            this.em.persist(entity);
        }
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    public <T extends BaseEntity> T insert(T entity) {
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setId(SnowflakeIDWorker.getInstance().generateId(entity.getClass()));
        }
        this.em.persist(entity);
        return entity;
    }

    @Transactional(rollbackFor = Exception.class)
    public <T> T update(T entity) {
        return this.em.merge(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public <T extends BaseEntity> List<T> batchInsert(Iterable<T> entities) {
        Assert.notNull(entities, "Entities must not be null!");
        List<T> result = new ArrayList();
        entities.forEach(entity -> result.add(this.insert(entity)));
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void flush() {
        this.em.flush();
    }
}