package com.github.javabean;

import com.github.annotation.Component;
import com.github.interfaces.ClassFilter;
import com.github.resource.Directory;
import com.github.resource.FileList;
import com.github.resource.ResourceUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 康盼Java开发工程师
 */
public class BeanClassScanner {
    private final String xml;

    protected Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public BeanClassScanner(String xml) {
        this.xml = xml;
    }

    public Set<Class> scan() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(xml);
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> beans = root.elements("bean");
            for (Element bean : beans) {
                String id = bean.attributeValue("id");
                String clazz = bean.attributeValue("class");
                Class beanClass = Class.forName(clazz);
                List<Element> properties = bean.elements("property");
                BeanProperties beanProperties = new BeanProperties();
                for (Element property : properties) {
                    String name = property.attributeValue("name");
                    String value = property.attributeValue("value");
                    String ref = property.attributeValue("ref");
                    Class valueType = Class.forName(property.attributeValue("class"));
                    BeanProperty beanProperty;
                    if (valueType == Long.class) {
                        beanProperty = new BeanProperty(name, Long.valueOf(value));
                    } else if (valueType == Integer.class) {
                        beanProperty = new BeanProperty(name, Integer.valueOf(value));
                    } else {
                        beanProperty = new BeanProperty(name, value);
                    }
                    if (!(ref == null || ref == "")) {
                        beanProperty = new BeanProperty(name, new BeanRef(ref));
                    }
                    beanProperties.add(beanProperty);
                }
                BeanDefinition beanDefinition = new BeanDefinition(beanClass, beanProperties);
                beanDefinitionMap.put(id, beanDefinition);
            }
            Element componentScan = root.element("component-scan");
            Set<Class> set = new LinkedHashSet<>();
            if (componentScan != null) {
                String scanPaths = componentScan.attributeValue("base-package");
                String[] packagePaths = scanPaths.split(",");
                for (String scanPath : packagePaths) {
                    String packagePath = scanPath.replace(".", "/");
                    for (URL url : ResourceUtil.getResourcesIterator(packagePath)) {
                        File file = new File(URLDecoder.decode(url.toString(), StandardCharsets.UTF_8.name()).substring(6));
                        FileList list = Directory.get(file, ".class");
                        list.getFiles().forEach(ele -> {
                            String fileName = ele.getName();
                            String className = scanPath + "." + fileName.substring(0, fileName.lastIndexOf("."));
                            try {
                                Class clazz = Class.forName(className, false, classLoader);
                                ClassFilter<Class<?>> filter = cla -> cla.isAnnotationPresent(Component.class);
                                if (filter.accept(clazz)) {
                                    set.add(clazz);
                                }
                            } catch (ClassNotFoundException exception) {
                                throw new RuntimeException(exception);
                            }
                        });
                    }
                }
            }
            return set;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
