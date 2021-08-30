package com.github.javabean;

import com.github.interfaces.BeanDriver;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author 康盼Java开发工程师
 */
public class BeanInit implements BeanDriver {

    public static void init(String name) {
        Set<String> keySet = cache.keySet();
        if (keySet.contains(name)) {
            BeanDefinition beanDefinition = Beans.classScanner.beanDefinitionMap.get(name);
            Object bean = cache.get(name);
            if (beanDefinition != null) {
                String initMethodName = beanDefinition.getInitMethodName();
                if (!(initMethodName == null || initMethodName.length() == 0)) {
                    try {
                        Method initMethod = bean.getClass().getMethod(initMethodName);
                        initMethod.invoke(bean);
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }
    }
}
