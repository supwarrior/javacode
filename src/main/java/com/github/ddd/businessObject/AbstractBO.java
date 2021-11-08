package com.github.ddd.businessObject;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.ddd.CoreAttribute;
import com.github.ddd.CoreBeanMapping;
import com.github.ddd.domainObject.ChildEntity;
import com.github.ddd.jpa.CoreJpaRepository;
import com.github.ddd.jpa.JpaRepository;
import com.github.ddd.SpringContextUtil;
import com.github.ddd.domainObject.MainEntity;
import com.github.jpa.lock.ObjectLockExecutor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 执行业务相关逻辑 抽象出来
 *
 * @author 康盼Java开发工程师
 */
@Data
@JsonIgnoreProperties(value = {"coreBeanMapping","jpaRepository"})
public abstract class AbstractBO<T extends MainEntity> implements BaseBO {

    private CoreBeanMapping coreBeanMapping;

    private JpaRepository jpaRepository;

    private ObjectLockExecutor objectLockExecutor;

    protected CoreJpaRepository coreJpaRepository;



    /**
     * 对应 DO 层的数据实体
     */
    private T entity;

    public AbstractBO(T entity) {
        coreBeanMapping = SpringContextUtil.getSingletonBean(CoreBeanMapping.class);
        jpaRepository = SpringContextUtil.getSingletonBean(JpaRepository.class);
        objectLockExecutor = SpringContextUtil.getSingletonBean(ObjectLockExecutor.class);
        coreJpaRepository = SpringContextUtil.getSingletonBean(CoreJpaRepository.class);
        this.entity = entity;
    }


    @Override
    public void flushMain() {
        jpaRepository.save(this.entity);
    }

    @Override
    public void flush() {
        jpaRepository.insert(this.entity);
    }

    @Override
    public boolean lock() {
        objectLockExecutor.executeLock(this.entity.getId());
        return true;
    }

    private Map<Class<?>, CoreAttribute<?>> attributes = new HashMap<>();

    protected <C extends ChildEntity> CoreAttribute<C> getAttribute(Class<C> attributeType) {
        return Optional.ofNullable((CoreAttribute<C>) attributes.get(attributeType)).orElseGet(() -> {
            CoreAttribute<C> attribute = new CoreAttribute<>(coreJpaRepository, attributeType, entity.getId());
            this.attributes.put(attributeType, attribute);
            return attribute;
        });
    }
}