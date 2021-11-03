package com.github.ddd.businessObject;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.ddd.CoreBeanMapping;
import com.github.ddd.JpaRepository;
import com.github.ddd.SpringContextUtil;
import com.github.ddd.domainObject.MainEntity;
import com.github.jpa.lock.ObjectLockExecutor;
import lombok.Data;

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

    /**
     * 对应 DO 层的数据实体
     */
    private T entity;

    public AbstractBO(T entity) {
        coreBeanMapping = SpringContextUtil.getSingletonBean(CoreBeanMapping.class);
        jpaRepository = SpringContextUtil.getSingletonBean(JpaRepository.class);
        objectLockExecutor = SpringContextUtil.getSingletonBean(ObjectLockExecutor.class);
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
}