package com.github.javabean;

import com.github.annotation.Alias;

import java.lang.reflect.Field;
import java.util.HashMap;
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

    public void initialize(Object bean) {
        Class clazz = bean.getClass();
        String id = Beans.getName(clazz);
        BeanDefinition beanDefinition = beanClassScanner.beanDefinitionMap.get(id);
        if (beanDefinition != null) {
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
                if (value instanceof BeanRef) {
                    BeanRef beanRef = (BeanRef) value;
                    String beanId = beanRef.getId();
                    value = Beans.cache.getOrDefault(beanId, Beans.getByName(beanId));
                }
                final Class fieldType = field.getType();
                if (value == null) {
                    if (fieldType.isPrimitive()) {
                        if (long.class == fieldType) {
                            value = 0L;
                        } else if (int.class == fieldType) {
                            value = 0;
                        } else if (short.class == fieldType) {
                            value = (short) 0;
                        } else if (char.class == fieldType) {
                            value = (char) 0;
                        } else if (byte.class == fieldType) {
                            value = (byte) 0;
                        } else if (double.class == fieldType) {
                            value = 0D;
                        } else if (float.class == fieldType) {
                            value = 0f;
                        } else if (boolean.class == fieldType) {
                            value = false;
                        }
                    } else {
                        if (Long.class == fieldType) {
                            value = 0L;
                        } else if (Integer.class == fieldType) {
                            value = 0;
                        } else if (String.class == fieldType) {
                            value = "";
                        }
                    }
                }
                if (fieldType.isAssignableFrom(value.getClass())) {
                    if (false == field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    try {
                        field.set(bean, value);
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }
    }
}
