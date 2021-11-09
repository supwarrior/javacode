package com.github.ddd;

import com.github.annotation.NamedIdentifier;
import com.github.ddd.domainObject.BaseEntity;
import com.github.ddd.domainObject.ChildEntity;
import com.github.ddd.jpa.Content;
import com.github.ddd.jpa.CoreJpaRepository;
import lombok.SneakyThrows;
import org.springframework.data.domain.Example;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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


    public static Field findField(Class type, String fieldName) {
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

    public static Field findIdentifierField(Class type) {
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

    public class DictionaryContent extends Content {



        public DictionaryContent() {
            super();
        }

        public DictionaryContent(Field field) {
            super(field);
        }

        public <T extends BaseEntity> Optional<T> get(String dKey) {
            return (Optional<T>) coreJpaRepository.findOne(dictionaryExample(dKey));
        }

        private Example<T> dictionaryExample(String dKey) {
            Example<T> example = example();
            setDkey(dKey, example.getProbe());
            return example;
        }
        @SneakyThrows
        protected Example<T> example() {
            T example = type.newInstance();
            example.setReferenceKey(CoreAttribute.this.referenceKey);
            return Example.of(example);
        }
        private void setDkey(String dKey, T entity) {
            entity.setReferenceKey(CoreAttribute.this.referenceKey);
            ReflectionUtils.makeAccessible(this.field);
            ReflectionUtils.setField(this.field, entity, dKey);
        }
    }
}

