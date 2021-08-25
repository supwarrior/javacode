package com.github.javabean;


import com.github.interfaces.BeanDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 康盼Java开发工程师
 * @description 获取bean
 */
public class Beans implements BeanDriver {
    private static BeanClassScanner classScanner = new BeanClassScanner("bean.xml");
    private static BeanInitialize beanInitialize = new BeanInitialize(classScanner);
    private static Set<Class> set = classScanner.scan();

    /**
     * @param name
     * @return
     */
    public static Object getByName(String name) {
        Object result = cache.get(name);
        if (result == null) {
            String names = set.stream().map(clazz -> getName(clazz)).collect(Collectors.joining(","));
            if (names.contains(name)) {
                for (Class clazz : set) {
                    String className = getName(clazz);
                    if (className.equals(name)) {
                        return initialize(clazz);
                    }
                }
            } else {
                throw new RuntimeException("no @Component");
            }
        }
        return result;
    }

    protected static String getName(Class clazz) {
        char[] chars = clazz.getSimpleName().toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    /**
     * get bean Map
     *
     * @param beanClass beanClass
     * @return Map<String, Object>
     */
    public static Object getByType(Class beanClass) {
        String name = getName(beanClass);
        Object result = cache.get(name);
        if (set.contains(beanClass)) {
            for (Class clazz : set) {
                if (clazz.isAssignableFrom(beanClass)) {
                    return initialize(clazz);
                }
            }
        } else {
            Class[] classes;
            if (beanClass.isInterface()) {
                classes = new Class[]{beanClass};
            } else {
                classes = beanClass.getInterfaces();
            }
            for (Class clazz : classes) {
                BeanLoader beanLoader = BeanLoader.load(clazz);
                Iterator iterator = beanLoader.iterator();
                while (iterator.hasNext()) {
                    Object obj = clazz.cast(iterator.next());
                    if (beanClass.isAssignableFrom(obj.getClass())) {
                        initialize(obj);
                        return obj;
                    }
                }
            }
        }

        return result;
    }

    private static Object initialize(Class clazz) {
        try {
            Object bean = clazz.getDeclaredConstructor().newInstance();
            initialize(clazz.cast(bean));
            return bean;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static void putCache(Object obj) {
        String name = getName(obj.getClass());
        cache.put(name, obj);
    }

    private static void initialize(Object bean) {
        beanInitialize.initialize(bean);
        BeanInject.set(bean);
        putCache(bean);
    }
}