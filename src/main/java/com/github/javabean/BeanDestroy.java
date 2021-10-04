package com.github.javabean;

import java.lang.reflect.Method;
import java.util.Set;


/**
 * @author 康盼Java开发工程师
 */
public class BeanDestroy extends BeanInit implements BeanDriver {

    /**
     *  Runtime.getRuntime().addShutdownHook JVM退出的时候调用
     */
    public static void destroy() {
        Runtime.getRuntime().addShutdownHook(new Thread(BeanDestroy::close));
        new Thread(BeanDestroy::close).start();
    }

    private static void close() {
        Set<String> keySet = cache.keySet();
        Object[] keys = keySet.toArray();
        for (int i = keys.length - 1; i >= 0; i--) {
            Object name = keys[i];
            BeanDefinition beanDefinition = Beans.classScanner.beanDefinitionMap.get(name);
            Object bean = cache.get(name);
            if (beanDefinition != null) {
                String destroyMethodName = beanDefinition.getDestroyMethodName();
                if (!(destroyMethodName == null || destroyMethodName.length() == 0)) {
                    try {
                        Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
                        destroyMethod.invoke(bean);
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
            cache.remove(name);
        }
    }

}
