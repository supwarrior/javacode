package com.github.ddd.factory;

import com.github.ddd.CoreBeanMapping;
import com.github.ddd.CoreInvocationHandler;
import com.github.ddd.SpringContextUtil;
import com.github.ddd.domainObject.BaseEntity;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
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
            }
        } catch (Exception exception) {
            throw new RuntimeException("DefaultBeanFactory getBean error => {}", exception);
        }
        return (T) new CoreInvocationHandler(instance).getProxy();
    }
}