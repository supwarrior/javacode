package com.github.ddd;

import com.github.annotation.Flush;
import com.github.ddd.businessObject.BaseBO;
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

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), this.target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.target, args);
        doExecutorFlush(this.target, method);
        return result;
    }

    private void doExecutorFlush(Object target, Method method) {
        try {
            this.findAnnotationFromProxy(Flush.class, target, method).ifPresent(flush -> {
                BaseBO baseBO = (BaseBO) target;
                switch (flush.scope()) {
                    case MAIN:
                        baseBO.flushMain();
                        break;
                    case ATTRIBUTE:
                        baseBO.flushAttribute();
                        break;
                    case ALL:
                        baseBO.flush();
                }
            });
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private <T extends Annotation> Optional<T> findAnnotationFromProxy(Class<T> cusAnnotation, Object target, Method method) throws NoSuchMethodException {
        Annotation[] annotations = target.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotations();
        Optional optional = Optional.ofNullable(
                Arrays.stream(annotations)
                        .filter(annotation -> annotation.annotationType().isAssignableFrom(cusAnnotation))
                        .findAny()).get();
        return optional;
    }
}