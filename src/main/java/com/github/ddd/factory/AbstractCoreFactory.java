package com.github.ddd.factory;

import com.github.annotation.NamedIdentifier;
import com.github.ddd.domainObject.BaseEntity;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 康盼Java开发工程师
 */
public abstract class AbstractCoreFactory implements BaseCoreFactory {

    @PersistenceContext
    private EntityManager entityManager;

    <T extends BaseEntity> T getEntityById(Class<T> clazz, Object id) {
        return this.entityManager.find(clazz, id);
    }

    public Field getNamedIdentifierFiled(Class<?> clazz) {
        AtomicReference<Field> fieldAtomicReference = new AtomicReference();
        ReflectionUtils.FieldCallback fieldCallback = field -> {
            NamedIdentifier namedIdentifier = field.getAnnotation(NamedIdentifier.class);
            if (namedIdentifier != null) {
                fieldAtomicReference.set(field);
            }
        };
        ReflectionUtils.doWithFields(clazz, fieldCallback);
        return fieldAtomicReference.get();
    }

    public List getEntityByNamedIdentifier(Class<?> clazz, String identifier) {
        Field field = this.getNamedIdentifierFiled(clazz);
        Column column = field.getAnnotation(Column.class);
        Table table = clazz.getAnnotation(Table.class);
        String sql = String.format("select * from %s t where t.%s = ?", table.name(), column.name());
        Query nativeQuery = this.entityManager.createNativeQuery(sql);
        nativeQuery.setParameter(1, identifier);
        return nativeQuery.getResultList();
    }
}