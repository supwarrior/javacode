package com.github.ddd.jpa.esec;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


@NoRepositoryBean
public interface BaseRepository<T, Id extends Serializable>
        extends JpaRepository<T, Id>, JpaSpecificationExecutor<T>, Serializable {

    JPAQueryFactory generateQuery();


}
