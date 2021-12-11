package com.github.common.util;

import com.github.annotation.SerializableFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    private static final Map<SerializableFunction<?, ?>, Field> CACHE = new ConcurrentHashMap<>();

    public static <T, R> String getFieldName(SerializableFunction<T, R> function) {
        Field field = ReflectionUtil.getField(function);
        return field.getName();
    }

    public static Field getField(SerializableFunction<?, ?> function) {
        return CACHE.computeIfAbsent(function, ReflectionUtil::findField);
    }

    public static Field findField(SerializableFunction<?, ?> function) {
        Field field = null;
        String fieldName = null;
        try {
            // 第1步 获取SerializedLambda
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
            // 第2步 implMethodName 即为Field对应的Getter方法名
            String implMethodName = serializedLambda.getImplMethodName();
            if (implMethodName.startsWith("get") && implMethodName.length() > 3) {
                fieldName = Introspector.decapitalize(implMethodName.substring(3));
            } else if (implMethodName.startsWith("is") && implMethodName.length() > 2) {
                fieldName = Introspector.decapitalize(implMethodName.substring(2));
            } else if (implMethodName.startsWith("lambda$")) {
                throw new IllegalArgumentException("SerializableFunction Lambda expressions cannot be passed, only method references can be used");
            } else {
                throw new IllegalArgumentException(implMethodName + "It's not a Getter method reference");
            }
            // 第3步 获取的Class是字符串，并且包名是“/”分割，需要替换成“.”，才能获取到对应的Class对象
            String declaredClass = serializedLambda.getImplClass().replace("/", ".");
            Class<?> aClass = Class.forName(declaredClass, false, ClassUtils.getDefaultClassLoader());

            // 第4步  Spring 中的反射工具类获取Class中定义的Field
            field = ReflectionUtils.findField(aClass, fieldName);

        } catch (Exception e) {
            log.error("findField error: {}", e.getMessage());
        }
        // 第5步 如果没有找到对应的字段应该抛出异常
        if (field != null) {
            return field;
        }
        throw new NoSuchFieldError(fieldName);
    }
}
