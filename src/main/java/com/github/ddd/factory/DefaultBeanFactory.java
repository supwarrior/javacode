package com.github.ddd.factory;

import com.github.annotation.Inject;
import com.github.ddd.CoreBeanMapping;
import com.github.ddd.CoreInvocationHandler;
import com.github.ddd.SpringContextUtil;
import com.github.ddd.domainObject.BaseEntity;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author 康盼Java开发工程师
 */
public class DefaultBeanFactory implements BeanFactory {

    private static final CoreBeanMapping coreBeanMapping = SpringContextUtil.getSingletonBean(CoreBeanMapping.class);

    @Override
    public <T> T getBean(Class<T> clazz, Object... args) {
        Class targetClass;
        Object instance = null;
        try {
            if (Modifier.isInterface(clazz.getModifiers())) {
                targetClass = coreBeanMapping.getCoreBean(clazz);
            } else {
                targetClass = clazz;
            }
            boolean matches = Arrays.stream(args).allMatch(arg -> BaseEntity.class.isAssignableFrom(arg.getClass()));
            if (matches) {
                Class entityClass = coreBeanMapping.getBean(clazz);
                Constructor constructor = Class.forName(targetClass.getName()).getDeclaredConstructor(entityClass);
                ReflectionUtils.makeAccessible(constructor);
                instance = constructor.newInstance(args);
                this.populateBean(targetClass, instance);
            }
        } catch (Exception exception) {
            throw new RuntimeException("DefaultBeanFactory getBean error => {}", exception);
        }
        return (T) new CoreInvocationHandler(instance).getProxy();
    }

    private void populateBean(Class targetClass, Object instance) {
        Field[] declaredFields = targetClass.getDeclaredFields();
        Arrays.stream(declaredFields)
                .parallel()
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    ReflectionUtils.makeAccessible(field);
                    Object bean = SpringContextUtil.getSingletonBean(field.getType());
                    ReflectionUtils.setField(field, instance, bean);
                });

        // 递归调用
        if (targetClass != Object.class) {
            this.populateBean(targetClass.getSuperclass(), instance);
        }
    }
}