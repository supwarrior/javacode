package com.github.javabean;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 康盼Java开发工程师
 * @description 获取bean
 */
public class Beans {
    private static BeanClassScanner classScanner = new BeanClassScanner("bean.xml");
    private static BeanInitialize beanInitialize = new BeanInitialize(classScanner);
    protected static final Map<String, Object> cache = new HashMap<>();
    private static Set<Class> set = classScanner.scan();

    /**
     * getByName 必须加 @Component 注解
     *
     * @param name
     * @return
     */
    public static Object getByName(String name) {
        Object result = cache.get(name);
        if (result == null) {
            String names = set.stream().map(e -> {
                char[] chars =  e.getSimpleName().toCharArray();
                chars[0] += 32;
                return new String(chars);
            }).collect(Collectors.joining(","));
            if (names.contains(name)) {
                for (Class clazz : set) {
                    char[] chars = clazz.getSimpleName().toCharArray();
                    chars[0] += 32;
                    String className = new String(chars);
                    if (className.equals(name)) {
                        return initialize(clazz);
                    }
                }
            } else {
                // todo
            }

        }
        return result;
    }

    /**
     * get bean Map
     *
     * @param beanClass beanClass
     * @return Map<String, Object>
     */
    public static Object getByType(Class beanClass) {
        char[] chars = beanClass.getSimpleName().toCharArray();
        chars[0] += 32;
        String name = new String(chars);
        Object result = cache.get(name);
        if (set.contains(beanClass)) {
            for (Class clazz : set) {
                if (clazz.isAssignableFrom(beanClass)) {
                    return initialize(clazz);
                }
            }
        } else {
            Class[] classes = beanClass.getInterfaces();
            for (Class clazz : classes) {
                BeanLoader beanLoader = BeanLoader.load(clazz);
                Iterator iterator = beanLoader.iterator();
                while (iterator.hasNext()) {
                    Object obj = clazz.cast(iterator.next());
                    if (obj.getClass().isAssignableFrom(beanClass)) {
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
        char[] chars = obj.getClass().getSimpleName().toCharArray();
        chars[0] += 32;
        String name = new String(chars);
        cache.put(name, obj);
    }

    private static void initialize(Object bean) {
        beanInitialize.initialize(bean);
        BeanInject.set(bean);
        putCache(bean);
    }
}