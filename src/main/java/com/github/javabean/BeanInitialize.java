package com.github.javabean;

import com.github.annotation.Alias;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * bean 初始化属性
 *
 * @author 康盼Java开发工程师
 */
public class BeanInitialize {

    private BeanClassScanner beanClassScanner;

    public BeanInitialize(BeanClassScanner beanClassScanner) {
        this.beanClassScanner = beanClassScanner;
    }

    public void initialize(Class clazz, Object bean) {
        String id = clazz.getSimpleName().toLowerCase(Locale.getDefault());
        BeanDefinition beanDefinition = beanClassScanner.beanDefinitionMap.get(id);
        BeanProperties beanProperties = beanDefinition.getProperties();
        Map<String, Object> fieldMap = new HashMap<>(64);
        for (BeanProperty beanProperty : beanProperties.getProperties()) {
            fieldMap.put(beanProperty.getName(), beanProperty.getValue());
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name;
            final Alias alias = field.getAnnotation(Alias.class);
            if (null != alias) {
                name = alias.value();
            } else {
                name = field.getName();
            }
            Object value = fieldMap.getOrDefault(name, null);
            final Class fieldType = field.getType();
            if (value != null) {
                if (fieldType.isAssignableFrom(value.getClass())) {
                    if (false == field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    try {
                        field.set(bean instanceof Class ? null : bean, value);
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }
    }
}
