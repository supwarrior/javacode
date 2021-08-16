package com.github.javabean;

import com.github.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 * @description bean inject 注入
 */
public class BeanInject {

    /**
     * inject 注解实例化
     *
     * @param bean
     */
    public static void set(Object bean) {
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class fieldType = field.getType();
                if (!field.getType().isInterface()) {
                    throw new RuntimeException("Inject field must be declared as an interface:" + field.getName() + "@Class" + clazz.getName());
                }
                Map<String, Object> beans = Beans.get(fieldType);
                if (beans.isEmpty()) {
                    throw new RuntimeException("META-INF.services not fond bean");
                }
                String beanName = field.getAnnotation(Inject.class).value();
                Object value;
                if (beanName == null || "".equals(beanName)) {
                    value = beans.values().iterator().next();
                } else {
                    char[] chars = beanName.toCharArray();
                    chars[0] -= 32;
                    String newBeanName = String.valueOf(chars);
                    value = beans.get(newBeanName);
                }
                try {
                    if (value != null) {
                        field.setAccessible(true);
                        field.set(bean, value);
                    } else {
                        throw new NullPointerException("no bean fond,check @Inject value");
                    }
                } catch (IllegalAccessException exception) {
                    throw new RuntimeException("Inject error");
                }
            }
        }
    }
}