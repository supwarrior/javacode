package com.github.ddd;

import com.github.annotation.Core;
import com.github.ddd.domainObject.BaseEntity;
import com.github.ddd.domainObject.ChildEntity;
import com.github.ddd.domainObject.MainEntity;
import com.github.mycim.event.EventFIFOBaseDO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储 DO BO
 * 用来转换获取对应的 DO BO
 *
 * @author 康盼Java开发工程师
 */
@Component
@Slf4j
public class CoreBeanMapping {


    private final Map<Class<?>, Class<?>> CORE_BEAN_MAPPING = new ConcurrentHashMap(256);
    private final Map<Class<?>, Class<?>> BO_AND_ENTITY_MAPPING = new ConcurrentHashMap(256);

    CoreBeanMapping() {
        init();
    }

    private void init() {
        log.info("CoreBeanMapping init...");
        initCoreBean();
    }

    public Class<?> getCoreBean(Class<?> key) {
        return this.CORE_BEAN_MAPPING.get(key);
    }

    public Class<?> getEntityBean(Class<?> key) {
        return this.BO_AND_ENTITY_MAPPING.get(key);
    }

    public Class<?> getBean(Class<?> key) {
        if (Modifier.isInterface(key.getModifiers())) {
            key = this.getCoreBean(key);
        }
        return getEntityBean(key);
    }

    private final Map<Class<?>, CoreBeanMapping.EntityMetadata> MASTER_AND_CHILD_ENTITY_MAPPING = new ConcurrentHashMap(256);

    public CoreBeanMapping.EntityMetadata getEntityMetadata(Class<? extends MainEntity> masterEntity) {
        return this.MASTER_AND_CHILD_ENTITY_MAPPING.get(masterEntity);
    }

    @Data
    public static class EntityMetadata {
        private Set<Class<? extends ChildEntity>> childEntities = new HashSet();
        private Class<? extends EventFIFOBaseDO> eventFIFOClass;
    }

    private void initCoreBean() {
        String scanValues = "com.github.ddd;com.github.analysis;com.github.mycim.boCell;com.github.mycim.event";
        Arrays.stream(scanValues.split(";")).forEach(scanValue -> {
            new Reflections(scanValue, new Scanner[0])
                    .getTypesAnnotatedWith(Core.class)
                    .stream()
                    .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                    .forEach(clazz -> {
                        Class<?>[] interfaces = clazz.getInterfaces();
                        if (interfaces.length > 0) {
                            Class<?> clazzInterface = clazz.getInterfaces()[0];
                            this.CORE_BEAN_MAPPING.put(clazzInterface, clazz);
                            Type type = clazz.getGenericSuperclass();
                            if (type instanceof ParameterizedType) {
                                ParameterizedType parameterizedType = (ParameterizedType) type;
                                // 获取泛型参数
                                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                                if (actualTypeArguments != null && actualTypeArguments.length >= 1 && actualTypeArguments[0] instanceof Class) {
                                    Class<?> entityClass = Arrays.stream(actualTypeArguments)
                                            .filter(actualTypeArgument -> BaseEntity.class.isAssignableFrom((Class) actualTypeArgument))
                                            .findAny()
                                            .map(ele -> (Class) ele)
                                            .orElseThrow(() -> new RuntimeException("Data Object Type Not Found"));
                                    BO_AND_ENTITY_MAPPING.put(clazz, entityClass);
                                    CoreBeanMapping.EntityMetadata entityInformation = new CoreBeanMapping.EntityMetadata();
                                    this.MASTER_AND_CHILD_ENTITY_MAPPING.putIfAbsent(entityClass, entityInformation);
                                }
                            }
                        }
                    });
        });
    }
}