package com.github.ddd.factory;


import com.github.ddd.domainObject.Person;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public interface BaseCoreFactory {

    /**
     * 根据主键获取实体对象数据
     *
     * @param clazz
     * @param primaryKey
     * @param <T>
     * @return
     */
    <T> T getBO(Class<T> clazz, String primaryKey);

    /**
     * 根据自定义注解 NamedIdentifier 获取对应的属性进行查找
     *
     * @param <T>
     * @param clazz
     * @param namedIdentifier
     * @return
     */
    <T> List<T> getBOByNamedIdentifier(Class<Person> clazz, String namedIdentifier);
}