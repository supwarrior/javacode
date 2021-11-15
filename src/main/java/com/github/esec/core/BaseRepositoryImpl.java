package com.github.esec.core;

import com.github.ddd.jpa.esec.BaseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryImpl<T, Id extends Serializable> extends SimpleJpaRepository<T, Id>
        implements BaseRepository<T, Id> {


    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager entityManager;


    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public JPAQueryFactory generateQuery() {
        return jpaQueryFactory;
    }
}
