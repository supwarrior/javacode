package com.github.common;

import com.github.annotation.Flush;
import com.github.code.AbstractCimBO;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author 康盼Java开发工程师
 */
public class CoreInvocationHandler implements InvocationHandler {

    private final Object target;

    public CoreInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.doExecutorFlush(this.target, method);
        return method.invoke(this.target, args);
    }

    public void doExecutorFlush(Object target, Method method) {
        try {
            this.findAnnotationFromProxy(Flush.class, target, method).ifPresent((flush) -> {
                AbstractCimBO<?> cimBO = (AbstractCimBO)target;
                switch(flush.scope()) {
                    case MAIN:
//                        cimBO.flushMain(); 跟JPA有关
                        break;
                    case ATTRIBUTE:
//                        cimBO.flushAttribute();
                        break;
                    case ALL:
//                        cimBO.flush();
                }

            });
        } catch (Throwable var4) {
        }
    }

    private <T extends Annotation> Optional<T> findAnnotationFromProxy(Class<T> cusAnnotation, Object target, Method method) throws NoSuchMethodException {
        Annotation[] annotations = target.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotations();
        return (Optional<T>) Optional.ofNullable(Arrays.stream(annotations).filter((annotation) ->
                annotation.annotationType().isAssignableFrom(cusAnnotation)).findAny().get());

    }

    public ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getDefaultClassLoader(), this.target.getClass().getInterfaces(), this);
    }
}