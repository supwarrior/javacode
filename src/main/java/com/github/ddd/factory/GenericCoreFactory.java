package com.github.ddd.factory;

import com.github.ddd.CoreBeanMapping;
import com.github.ddd.domainObject.BaseEntity;
import com.github.ddd.businessObject.Person;
import com.github.ddd.jpa.CoreHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class GenericCoreFactory extends AbstractCoreFactory {

    @Autowired
    private CoreBeanMapping coreBeanMapping;

    @Autowired
    private CoreHelper coreHelper;

    /**
     * getBO
     *
     * @param clazz
     * @param primaryKey
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBO(Class<T> clazz, String primaryKey) {
        Class entityClass = this.coreBeanMapping.getBean(clazz);
        BaseEntity entity = this.getEntityById(entityClass, primaryKey);
        // 这里做了特殊处理 按逻辑是不能为空的 否则查出来是有数据
        if (entity == null) {
            entity = this.coreHelper.newEntity(entityClass);
        }
        // 这里可以加入缓存支持
        T result = BeanFactory.getDefaultBeanFactory().getBean(clazz, new Object[]{entity});
        return result;
    }

    @Override
    public <T> List<T> getBOByNamedIdentifier(Class<Person> clazz, String namedIdentifier) {
        Class entityClass = this.coreBeanMapping.getBean(clazz);
        List list = this.getEntityByNamedIdentifier(entityClass, namedIdentifier);
        return list;
    }

    public <T> T getBO(Class<T> boClazz, BaseEntity entity) {
        if (Modifier.isInterface(boClazz.getModifiers())) {
            if (null == entity) {
                return null;
            } else {
                return BeanFactory.getDefaultBeanFactory().getBean(boClazz, new Object[]{entity});
            }
        }
        return null;
    }

    public <T> T newBO(Class<T> boClazz) {
        if (Modifier.isInterface(boClazz.getModifiers())) {
            Class<BaseEntity> baseEntity = (Class<BaseEntity>) this.coreBeanMapping.getBean(boClazz);
            return this.getBO(boClazz, this.coreHelper.newEntity(baseEntity));
        }
        return null;
    }
}