package com.github.code;

import com.github.annotation.Core;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/25     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/25 13:17
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Component
@Slf4j
public class CoreBeanMapping {
    /**
     * this.CORE_BEAN_MAPPING.put(anInterface, aClass);
     * interface com.fa.cim.newcore.bo.machine.CimEqpMonitor
     * class com.fa.cim.newcore.impl.bo.machine.CimEqpMonitorBO
     *
     * @Core
     * public class CimEqpMonitorBO extends AbstractNamedEntity<CimEquipmentMonitorDO> implements CimEqpMonitor {}
     *
     * {Class@7441} "interface com.fa.cim.newcore.bo.person.CimPerson" -> {Class@11254} "class com.fa.cim.newcore.impl.bo.person.CimPersonBO"
     */
    public final Map<Class<?>, Class<?>> CORE_BEAN_MAPPING = new ConcurrentHashMap(256);
    public final Map<Class<?>, Class<? extends BaseEntity>> BO_AND_ENTITY_MAPPING = new ConcurrentHashMap(256);
    public final Map<String, Class<? extends BaseEntity>> PREFIX_AND_ENTITY_MAPPING = new ConcurrentHashMap(256);

    private CoreBeanMapping() {
        log.info("init");
        this.initCoreBean();
    }

    /**
     * CoreBeanMapping
     * initCoreBean
     * GenericCoreFactory
     */
    private void initCoreBean() {
        (new Reflections("com.github.code", new Scanner[0]))
                .getTypesAnnotatedWith(Core.class).stream()
                .filter((aClass) -> !this.isAbstract(aClass)).forEach((aClass) -> {
            Class<?>[] interfaces = aClass.getInterfaces();
            if (interfaces.length > 0) {
                Class<?> anInterface = aClass.getInterfaces()[0];
                this.CORE_BEAN_MAPPING.put(anInterface, aClass);
                Type type = aClass.getGenericSuperclass();
                if(type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    if (actualTypeArguments != null && actualTypeArguments.length >= 1 && actualTypeArguments[0] instanceof Class) {
                        Class<? extends BaseEntity> boundEntity  = Arrays.stream(actualTypeArguments)
                                .filter(args -> BaseEntity.class.isAssignableFrom((Class)args)).findAny()
                                .map(ele -> (Class)ele).orElseThrow(() -> new RuntimeException("Data Object Type Not Found"));
                        BO_AND_ENTITY_MAPPING.put(aClass, boundEntity);
                    }
                }
            }
        });
    }

    public boolean isAbstract(Class<?> boClazz) {
        return Modifier.isAbstract(boClazz.getModifiers());
    }
}
