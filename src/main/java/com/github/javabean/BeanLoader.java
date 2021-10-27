package com.github.javabean;


import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static com.github.common.cons.Constant.FACTORIES_RESOURCE_LOCATION;

/**
 * Javabean 的加载器 SPI机制
 *
 * @author 康盼Java开发工程师
 */
public class BeanLoader<T> implements Iterable<T> {
    /**
     * bean 接口类型
     */
    private final Class<T> clazz;
    /**
     * 类加载器
     */
    private final ClassLoader classLoader;
    /**
     * bean 迭代器
     */
    private BeanIterator<T> beanIterator;

    /**
     * 私有构造
     *
     * @param clazz       接口类型
     * @param classLoader 类加载器
     */
    private BeanLoader(Class<T> clazz, ClassLoader classLoader) {
        this.clazz = Objects.requireNonNull(clazz, "interface cannot be null");
        this.classLoader = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
        this.beanIterator = new BeanIterator(clazz, classLoader);
    }

    /**
     * 实例化
     *
     * @param clazz 接口类型
     * @param <T>   接口
     * @return BeanLoader
     */
    public static <T> BeanLoader<T> load(Class<T> clazz) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new BeanLoader(clazz, classLoader);
    }

    @Override
    public Iterator<T> iterator() {
        return beanIterator;
    }


    /**
     * @return
     */
    public static Map<String, List<String>> loadSpringFactories() {
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> urls = (classLoader != null ?
                    classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
                    ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {
                    String factoryClassName = ((String) entry.getKey()).trim();
                    for (String factoryName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                        result.add(factoryClassName, factoryName.trim());
                    }
                }
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Unable to load factories from location [" +
                    FACTORIES_RESOURCE_LOCATION + "]", exception);
        }
        return result;
    }
}
