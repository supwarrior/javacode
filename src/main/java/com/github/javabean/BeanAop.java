package com.github.javabean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 康盼Java开发工程师
 * @description 生成bean 并赋值inject属性
 */
public class BeanAop implements InvocationHandler {

    private final Object bean;

    private BeanAop(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        BeanInject.set(bean);
        return method.invoke(bean, args);
    }

    public static Object instance(Class<?> beanClass) {
        try {
            Object bean = beanClass.getDeclaredConstructor().newInstance();
            BeanAop beanAop = new BeanAop(bean);
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), beanAop);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
