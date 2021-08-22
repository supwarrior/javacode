package com.github.javabean;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author 康盼Java开发工程师
 * @description 获取bean
 */
public class Beans {
    /**
     * getByType
     *
     * @param type
     * @return Object
     */
    public static Object getByType(Class type) {
        if (type.isInterface()) {
            Map<String, Object> map = get(type);
            if (map.isEmpty()) {
                return null;
            }
            if (map.size() > 1) {
                throw new RuntimeException("required a single bean, but 2 were found");
            }
            return map.values().iterator().next();
        }
        Class<?>[] clazz = type.getInterfaces();
        if (clazz != null && clazz.length == 1) {
            Map<String, Object> map = get(clazz[0]);
            return map.get(type.getSimpleName());
        }
        Map<String, Object> map = get(type);
        return map.get(type.getSimpleName());
    }

    /**
     * get bean Map
     *
     * @param beanClass beanClass
     * @return Map<String, Object>
     */
    public static Map<String, Object> get(Class beanClass) {
        Map<String, Object> result = new HashMap<>(64);
        BeanLoader beanLoader = BeanLoader.load(beanClass);
        Iterator iterator = beanLoader.iterator();
        while (iterator.hasNext()) {
            Object obj = beanClass.cast(iterator.next());
            if (Objects.nonNull(obj)) {
                BeanInject.set(obj);
                result.put(obj.getClass().getSimpleName(), obj);
            }
        }
        BeanClassScanner classScanner = new BeanClassScanner("bean.xml");
        Set<Class> set = classScanner.scan();
        for (Class clazz : set) {
            try {
                Object bean = clazz.getDeclaredConstructor().newInstance();
                BeanInitialize beanInitialize = new BeanInitialize(classScanner);
                beanInitialize.initialize(clazz,bean);
                if (clazz.isAssignableFrom(beanClass) && Objects.nonNull(bean)) {
                    BeanInject.set(bean);
                    result.put(clazz.getSimpleName(), clazz.cast(bean));
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        return result;
    }
}