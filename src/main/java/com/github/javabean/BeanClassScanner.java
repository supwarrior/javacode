package com.github.javabean;

import com.github.annotation.Component;
import com.github.resource.Directory;
import com.github.resource.FileList;
import com.github.resource.ResourceUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 康盼Java开发工程师
 */
public class BeanClassScanner {
    private final String xml;

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
            Element componentScan = root.element("component-scan");
            String scanPath = componentScan.attributeValue("base-package");
            String packagePath = scanPath.replace(".", "/");
            Set<Class> set = new LinkedHashSet<>();
            for (URL url : ResourceUtil.getResourcesIterator(packagePath)) {
                File file = new File(URLDecoder.decode(url.toString(), StandardCharsets.UTF_8.name()).substring(6));
                FileList list = Directory.get(file, ".*");
                list.getFiles().forEach(ele -> {
                    String fileName = ele.getName();
                    if (fileName.endsWith(".class")) {
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
                    }
                });
            }
            return set;
        } catch (DocumentException | UnsupportedEncodingException exception) {
            throw new RuntimeException(exception);
        }
    }
}
