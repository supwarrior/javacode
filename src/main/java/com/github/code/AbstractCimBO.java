package com.github.code;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 康盼Java开发工程师
 */
public abstract class AbstractCimBO<T extends BaseEntity> implements CimBO {

    @Autowired
    protected CoreJpaRepository coreJpaRepository;
}
