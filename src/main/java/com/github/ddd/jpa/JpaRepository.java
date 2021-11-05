package com.github.ddd.jpa;

import com.github.ddd.SnowflakeIDWorker;
import com.github.ddd.domainObject.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public <T extends BaseEntity> Page<T> query(String nativeSql, Class<T> classType, SearchCondition searchCondition, Object... params) {
        Query pagedQuery = getPagedQuery(getNativeQuery(nativeSql, classType, params), searchCondition);
        List<T> result = pagedQuery.getResultList();
        return getPage(result, searchCondition, countTotal(nativeSql, params));
    }

    private <T> Page<T> getPage(List<T> contents, SearchCondition searchCondition, long total) {
        return new PageImpl(contents, PageRequest.of(searchCondition.getPage() - 1, searchCondition.getSize()), total);
    }

    private <T> Query getNativeQuery(String nativeSql, Class<T> classType, Object[] params) {
        Query nativeQuery = em.createNativeQuery(nativeSql, classType);
        setParams(nativeQuery, params);
        return nativeQuery;
    }
    private Query generateNativeQuery(String nativeSql, Object... params) {
        Query nativeQuery = em.createNativeQuery(nativeSql);
        setParams(nativeQuery, params);
        return nativeQuery;
    }

    private Query getPagedQuery(Query query, SearchCondition searchCondition) {
        Integer size = searchCondition.getSize();
        Integer page = searchCondition.getPage() - 1;
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query;
    }

    private long countTotal(String nativeSql, Object... params) {
        String countSql = String.format("SELECT COUNT(*) FROM (%s) ct", nativeSql);
        return count(countSql, params);
    }

    public long count(String nativeSql, Object... params) {
        List<?> result = generateNativeQuery(nativeSql, params).getResultList();
        return CollectionUtils.isEmpty(result) ? 0L : result.size();
    }

    private void setParams(Query nativeQuery, Object... params) {
        int position = 0;
        for (int size = params.length; position < size; ++position) {
            nativeQuery.setParameter(position + 1, params[position]);
        }
    }
}