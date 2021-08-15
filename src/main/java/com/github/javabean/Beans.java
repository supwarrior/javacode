package com.github.javabean;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

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
    public static <T> Map<String, Object> get(Class beanClass) {
        Map<String, Object> result = new HashMap<>(64);
        BeanLoader beanLoader = BeanLoader.load(beanClass);
        Iterator iterator = beanLoader.iterator();
        while (iterator.hasNext()) {
            Object obj = beanClass.cast(iterator.next());
            if (Objects.nonNull(obj)) {
                result.put(obj.getClass().getSimpleName(), obj);
            }
        }
        return result;
    }
}