package com.github.javabean;


import com.github.resource.ClassScanner;

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
     * META-INF.services
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
                result.put(obj.getClass().getSimpleName(), obj);
            }
        }
        ClassScanner classScanner = new ClassScanner("bean.xml");
        Set<Class> set = classScanner.scan();
        for (Class clazz : set) {
            try {
                Object bean = clazz.getDeclaredConstructor().newInstance();
                result.put(clazz.getSimpleName(), bean);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
        return result;
    }
}