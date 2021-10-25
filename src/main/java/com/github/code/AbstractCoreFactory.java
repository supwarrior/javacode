package com.github.code;

import com.github.annotation.NamedIdentifier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CoreAssert
 * @author 康盼Java开发工程师
 */
public abstract class AbstractCoreFactory implements BaseCoreFactory {
    @PersistenceContext
    private EntityManager entityManager;


    public AbstractCoreFactory() {
    }

    <T> T getEntityById(Class<T> domainType, String id) {
        return this.entityManager.find(domainType, id);
    }

    private <T extends BaseEntity> Field getNamedIdentifierFiled(Class<T> entityType) {
        AtomicReference<Field> identifierField = new AtomicReference();
        ReflectionUtils.doWithFields(entityType, (field) -> {
            NamedIdentifier annotation =  field.getAnnotation(NamedIdentifier.class);
            if (annotation != null) {
                identifierField.set(field);
            }

        });
        return identifierField.get();
    }

    <T extends BaseEntity> T getEntityByIdentify(Class<T> entityType, ObjectIdentifier objectIdentifier) {
        if (StringUtils.isNotEmpty(objectIdentifier.getReferenceKey())) {
            return this.getEntityById(entityType, objectIdentifier.getReferenceKey());
        } else {
            String identifier = objectIdentifier.getValue();
            return this.getEntityByNamedIdentifier(entityType, identifier);
        }
    }

    <T extends BaseEntity> T getEntityByNamedIdentifier(Class<T> entityType, String identifier) {
        Field identifierField = this.getNamedIdentifierFiled(entityType);
        Column columnAnnotation = identifierField.getAnnotation(Column.class);
        Table tableAnnotation = entityType.getAnnotation(Table.class);

        assert tableAnnotation != null;

        assert columnAnnotation != null;

        String sql = String.format("select * from %s t where t.%s = ?", tableAnnotation.name(), columnAnnotation.name());
        Query nativeQuery = this.entityManager.createNativeQuery(sql, entityType);
        nativeQuery.setParameter(1, identifier);
        List<T> resultList = nativeQuery.getResultList();
        return CollectionUtils.isEmpty(resultList) ? null : resultList.get(0);
    }

    <T extends BaseEntity> List<T> getAllEntity(Class<T> entityClass) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        return this.entityManager.createQuery(query).getResultList();
    }
}
