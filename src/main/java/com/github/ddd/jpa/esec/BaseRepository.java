package com.github.ddd.jpa.esec;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T, Id extends Serializable>
        extends JpaRepository<T, Id>, JpaSpecificationExecutor<T>, Serializable {

    T update(Id id, T t);

    T updateAndFlush(Id id, T t);

    T findOne(Id id);

    T getFirst();

    List<T> nativeQuery(String sql, Map<String, Object> params);

    <S> List<S> nativeQuery(String sql);

    /**
     * @param sql 原生sql
     * @param resultClass 返回的数据类型(该类型中需要注解@TypeDef)
     */
    <S> List<S> nativeQuery(String sql, Class<S> resultClass, Map<String, Object> params);

    List<T> nativeQuery(String sql, Object... params);

    <S> List<T> nativeQuery(String sql, Class<S> resultClass, Object... params);

    <S> List<S> findBySql(String sql, Object... params);

    <S> List<S> findBySql(String sql, Map<String, Object> params);

    <S> List<S> findBySql(String sql, int pageNo, int pageSize);

    Long countBySql(String sql);

    Long countBySql(String sql, Map<String, Object> params);

    <S> List<S> findBySql(String sql, Class<S> resultClass, Object... params);

    List<T> nativeQuery(String sql, Map<String, Object> params, int pageNo, int pageSize);

    <S> List<S> nativeQuery(String sql, Class<S> resultClass, Map<String, Object> params, int pageNo, int pageSize);

    int updateBySql(String sql, Map<String, Object> params);

    List<T> findByExample(T t);

    <S> List<S> findByJpql(String jpql);

    <S> List<S> findByJpql(String jpql, PageParam pageParam, Map<String, Object> params);

    <S> Page<S> findPageByJpql(String jpql, Class<S> tClass, PageParam pageParam);

    <S> List<S> findByJpql(String jpql, Map<String, Object> params);

    <S> List<S> findByJpql(String jpql, Class<S> resultClass);

    <S> List<S> findByJpql(String jpql, int pageNo, int pageSize);

    Long countByJpql(String jpql);

    int updateByJpql(String jpql, Map<String, Object> params);

    <S> List<S> findByJpql(String jpql, Map<String, Object> params, Class<S> resultClass);

    <S> List<S> findByJpqlResultVO(String jpql, Map<String, Object> params, Class<S> resultClass);

    Long countByJpql(String jpql, Map<String, Object> params);

    String aggregateQuery(String sql);

    JPAQueryFactory generateQuery();


}
