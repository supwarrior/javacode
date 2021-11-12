package com.github.ddd.jpa.esec;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BasicRepository<T, Id extends Serializable> extends BaseRepository<T, Id>,
        QuerydslPredicateExecutor<T> {
}
