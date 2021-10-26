package com.github.code;

import com.github.common.CoreInvocationHandler;
import com.github.common.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author 康盼Java开发工程师
 */
public class DefaultBeanFactory implements BeanFactory {
    private static final CoreBeanMapping coreBeanMapping =  SpringContextUtil.getSingletonBean(CoreBeanMapping.class);

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        try {
            Class targetClass = requiredType;
            boolean allMatch = Arrays.stream(args).allMatch(arg -> BaseEntity.class.isAssignableFrom(arg.getClass()));
            if (allMatch) {
                Class<? extends BaseEntity> entityClass = getEntityBean(requiredType);
                Class aClass = Class.forName(targetClass.getName());
                Constructor constructor = aClass.getDeclaredConstructor(entityClass);
                ReflectionUtils.makeAccessible(constructor);
                Object instance = constructor.newInstance(args);
                this.populateBean(targetClass, instance);
                return (T) new CoreInvocationHandler(instance).getProxy();

            }
        } catch (Exception e) {

        }



        return null;
    }

    protected void populateBean(Class<?> targetClass, Object instance) {
        Field[] declaredFields = targetClass.getDeclaredFields();
        Arrays.stream(declaredFields).parallel().filter(field ->
            field.getAnnotation(Autowired.class) != null).forEach(field -> {
            ReflectionUtils.makeAccessible(field);
            Object bean = SpringContextUtil.getSingletonBean(field.getType());
            ReflectionUtils.setField(field, instance, bean);
        });
        if (targetClass != Object.class) {
            this.populateBean(targetClass.getSuperclass(), instance);
        }
    }

    public Class<? extends BaseEntity> getEntityBean(Class<?> key) {
        return (Class)coreBeanMapping.BO_AND_ENTITY_MAPPING.get(key);
    }

    public Class<? extends AbstractCimBO<?>> getCoreBean(Class<?> key) {
        return (Class)coreBeanMapping.CORE_BEAN_MAPPING.get(key);
    }

    public boolean isInterface(Class<?> boClazz) {
        return Modifier.isInterface(boClazz.getModifiers());
    }
}