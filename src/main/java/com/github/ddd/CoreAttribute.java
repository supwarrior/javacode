package com.github.ddd;

import com.github.annotation.NamedIdentifier;
import com.github.common.util.StringUtil;
import com.github.ddd.domainObject.BaseEntity;
import com.github.ddd.domainObject.ChildEntity;
import com.github.ddd.jpa.CoreJpaRepository;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CoreAttribute<T extends ChildEntity> {

    private final Field referenceField;
    private final Field identifierField;
    private final Class<T> type;
    private final String referenceKey;
    private final Map<String, T> toSaveCache;

    private final CoreJpaRepository coreJpaRepository;

    public CoreAttribute(CoreJpaRepository coreJpaRepository, Class<T> type, String referenceKey) {
        this.coreJpaRepository = coreJpaRepository;
        this.type = type;
        this.referenceKey = referenceKey;
        this.toSaveCache = new HashMap<>();
        this.referenceField = findField(type, COL_REFKEY);
        this.identifierField = findIdentifierField(type);
    }

    private static final String COL_REFKEY = "REFKEY";


    private static Field findField(Class type, String fieldName) {
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation != null && annotation.name().equals(fieldName)) {
                return field;
            }
        }
        Class superclass = type.getSuperclass();
        if (superclass != BaseEntity.class) {
            return findField(superclass, fieldName);
        }
        return null;
    }

    private static Field findIdentifierField(Class type) {
        Field[] declaredFields = type.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(NamedIdentifier.class)) {
                return declaredField;
            }
        }
        Class superclass = type.getSuperclass();
        if (superclass != ChildEntity.class) {
            return findIdentifierField(superclass);
        }
        return null;
    }
}

