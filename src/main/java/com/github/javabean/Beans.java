package com.github.javabean;



import com.github.annotation.Inject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.common.util.StringUtil.getName;

/**
 * @author 康盼Java开发工程师
 * @description 获取bean
 */
public class Beans extends BeanDestroy implements BeanDriver {
    protected static BeanClassScanner classScanner = new BeanClassScanner("bean.xml");
    protected static BeanInitialize beanInitialize = new BeanInitialize(classScanner);
    protected static Set<Class> set = classScanner.scan();

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
                BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/spring-bean.xml");
                if (beanFactory instanceof ListableBeanFactory) {
                    ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
                    Map<String, Object>  map = listableBeanFactory.getBeansWithAnnotation(Inject.class);
                    return map.get(name);
                }
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
        String name = getName(beanClass);
        Object result = cache.get(name);
        if (result == null) {
            if (set.contains(beanClass)) {
                for (Class clazz : set) {
                    if (clazz.isAssignableFrom(beanClass)) {
                        return initialize(clazz);
                    }
                }
            } else {
                // SPI
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

    /**
     * 实例化
     *
     * @param bean
     */
    private static void initialize(Object bean) {
        // 属性赋值
        beanInitialize.initialize(bean);
        // inject 赋值
        BeanInject.set(bean);
        String name = getName(bean.getClass());
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            Stream.of(beanInfo.getPropertyDescriptors()).forEach(descriptor -> System.out.println(descriptor));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        cache.put(name, bean);
    }
}