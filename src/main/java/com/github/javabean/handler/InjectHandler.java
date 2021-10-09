package com.github.javabean.handler;

import com.github.annotation.Component;
import com.github.annotation.Inject;
import com.github.javabean.Beans;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Supplier;


/**
 * inject 注入的时候获取属性值处理
 *
 * @author 康盼Java开发工程师
 */
@Component
public class InjectHandler implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                if (field.getType().isInterface()) {
                    try {
                        inject(field, bean, field.getType());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("inject error:" + e.getMessage());
                    }
                }
            }
        }
        return bean;
    }

    private void inject(Field field, Object bean, Class type) throws IllegalAccessException {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        String name = StringUtils.uncapitalize(field.getName());
        proxyFactory.addAdvice(new InjectMethodInterceptor(name, type));
        Object proxy = proxyFactory.getProxy();
        ReflectionUtils.makeAccessible(field);
        field.set(bean, proxy);
    }

    private class InjectMethodInterceptor implements MethodInterceptor {

        private String name;

        private Class type;

        InjectMethodInterceptor(String name, Class type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.getMethod().invoke(getValue(() -> applicationContext.getBean(name)), invocation.getArguments());
        }

        private Object getValue(Supplier supplier) {
            Object value = null;
            try {
                value = supplier.get();
            } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
                if (value == null) {
                    Map<String, Object> values = applicationContext.getBeansOfType(type);
                    if (values != null && values.size() == 1) {
                        value = values.values().iterator().next();
                    } else {
                        value = Beans.getByType(type);
                    }
                }
            }
            return value;
        }
    }
}
