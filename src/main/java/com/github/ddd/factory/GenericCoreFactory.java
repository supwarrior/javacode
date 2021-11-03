package com.github.ddd.factory;

import com.github.ddd.CoreBeanMapping;
import com.github.ddd.domainObject.BaseEntity;
import com.github.ddd.businessObject.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class GenericCoreFactory extends AbstractCoreFactory {

    @Autowired
    private CoreBeanMapping coreBeanMapping;

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
}