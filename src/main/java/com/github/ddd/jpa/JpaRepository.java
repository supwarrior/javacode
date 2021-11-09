package com.github.ddd.jpa;

import com.github.ddd.SnowflakeIDWorker;
import com.github.ddd.domainObject.BaseEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

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

    public static class ExampleSpecification<T> implements Specification<T> {
        private final Example<T> example;
        private final EscapeCharacter escapeCharacter;

        public ExampleSpecification(Example<T> example, EscapeCharacter escapeCharacter) {
            this.example = example;
            this.escapeCharacter = escapeCharacter;
        }

        @Nullable
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return QueryByExamplePredicateBuilder.getPredicate(root, cb, example, escapeCharacter);
        }
    }

    public <T extends BaseEntity> List<T> findAll(Example<T> example) {
        return this.isExampleValueEmpty(example.getProbe()) ?
                Collections.emptyList()
                : this.getQuery(new JpaRepository.ExampleSpecification(example, EscapeCharacter.DEFAULT),
                example.getProbeType(),
                Sort.unsorted()).getResultList();
    }

    protected <T extends BaseEntity> TypedQuery<T> getQuery(@Nullable Specification<T> spec, Class<T> domainClass, Sort sort) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(domainClass);
        Root<T> root = this.applySpecificationToCriteria(spec, domainClass, query);
        query.select(root);
        if (sort.isSorted()) {
            query.orderBy(QueryUtils.toOrders(sort, root, builder));
        }

        return this.em.createQuery(query);
    }

    private <S, U extends BaseEntity> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec,
                                                                           final Class<U> domainClass,
                                                                           CriteriaQuery<S> query) {

        Root<U> root = query.from(domainClass);
        if (null == spec) {
            return root;
        } else {
            CriteriaBuilder builder = this.em.getCriteriaBuilder();
            Predicate predicate = spec.toPredicate(root, query, builder);
            if (null != predicate) {
                query.where(predicate);
            }

            return root;
        }
    }

    private <T> boolean isExampleValueEmpty(T example) {
        return allFields(example.getClass()).parallelStream().noneMatch((field) -> {
            ReflectionUtils.makeAccessible(field);
            return null != ReflectionUtils.getField(field, example);
        });
    }

    public <R, T extends R> List<Field> allFields(Class<T> targetClass) {
        Class superclass = targetClass.getSuperclass();
        List<Field> superClassFields = superclass != Object.class ? allFields(superclass) : Collections.emptyList();
        Field[] declaredFields = targetClass.getDeclaredFields();
        List<Field> result = Arrays.stream(declaredFields)
                .filter((field) -> !Modifier.isStatic(field.getModifiers())).collect(Collectors.toList());
        result.addAll(superClassFields);
        return isEmpty(result) ? Collections.emptyList() : result;
    }

    public static boolean isEmpty(Collection<?> list) {
        return null == list || list.isEmpty();
    }
}