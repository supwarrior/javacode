package com.github.ddd.jpa;

import com.github.annotation.DefaultValue;
import com.github.ddd.SnowflakeIDWorker;
import com.github.ddd.domainObject.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CoreHelperImpl implements CoreHelper {


    public <E extends BaseEntity> E newEntity(Class<E> entityClazz) {
        try {
            E entity = entityClazz.newInstance();
            entity.setId(SnowflakeIDWorker.getInstance().generateId(entity.getClass()));
            this.initEntity(entity);
            return entity;
        } catch (Exception var3) {
            log.error("create Entity failed !", var3);
            return null;
        }
    }

    private <E extends BaseEntity> void initEntity(E entity) {
        Class<? extends BaseEntity> aClass = entity.getClass();
        Optional.of(this.findDefaultValueFields(aClass)).ifPresent(list -> {
            list.forEach(field -> {
                DefaultValue defaultValue = (DefaultValue) field.getAnnotation(DefaultValue.class);
                if (null != defaultValue) {
                    ReflectionUtils.makeAccessible(field);
                    Object value = ReflectionUtils.getField(field, entity);
                    if (null == value) {
                        Type genericType = field.getGenericType();
                        FiledTypeEnum fileType = FiledTypeEnum.getFileType(genericType.getTypeName());
                        switch (defaultValue.type()) {
                            case OTHER:
                                switch (fileType) {
                                    case CORE_STRING:
                                        ReflectionUtils.setField(field, entity, defaultValue.stringValue());
                                        return;
                                    case CORE_INT:
                                        ReflectionUtils.setField(field, entity, defaultValue.intValue());
                                        return;
                                    case CORE_DOUBLE:
                                        ReflectionUtils.setField(field, entity, defaultValue.doubleValue());
                                        return;
                                    case CORE_BOOLEAN:
                                        ReflectionUtils.setField(field, entity, defaultValue.boolValue());
                                        return;
                                    case CORE_CHAR:
                                        ReflectionUtils.setField(field, entity, defaultValue.charValue());
                                        return;
                                    case CORE_LONG:
                                        ReflectionUtils.setField(field, entity, defaultValue.longValue());
                                        return;
                                    case CORE_SHORT:
                                        ReflectionUtils.setField(field, entity, defaultValue.shortValue());
                                        return;
                                    case CORE_TIME:
                                        ReflectionUtils.setField(field, entity, new Timestamp(System.currentTimeMillis()));
                                        return;
                                    default:
                                        return;
                                }
                        }
                    }
                }
            });
        });

    }

    public enum FiledTypeEnum {
        CORE_INT(Integer.class, "java.lang.Integer"),
        CORE_STRING(String.class, "java.lang.String"),
        CORE_DOUBLE(Double.class, "java.lang.Double"),
        CORE_BOOLEAN(Boolean.class, "java.lang.Boolean"),
        CORE_LONG(Long.class, "java.lang.Long"),
        CORE_SHORT(Short.class, "java.lang.Short"),
        CORE_CHAR(Character.class, "java.lang.Character"),
        CORE_TIME(Timestamp.class, "java.sql.Timestamp");

        private final Class<?> type;
        private final String name;

        private FiledTypeEnum(Class<?> type, String name) {
            this.type = type;
            this.name = name;
        }

        public static FiledTypeEnum getFileType(String name) {
            FiledTypeEnum[] values = values();
            int length = values.length;

            for (int n = 0; n < length; ++n) {
                FiledTypeEnum value = values[n];
                if (value.getName().equals(name)) {
                    return value;
                }
            }
            return null;
        }

        public Class<?> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }


    }

    private List<Field> findDefaultValueFields(Class<?> aClass) {
        ArrayList retVal;
        for (retVal = new ArrayList(); aClass != Object.class; aClass = aClass.getSuperclass()) {
            List<Field> result = Arrays.stream(aClass.getDeclaredFields()).parallel()
                    .filter(field -> Objects.nonNull(field.getAnnotation(DefaultValue.class)))
                    .collect(Collectors.toList());
            retVal.addAll(result);
        }
        return retVal;
    }

}
