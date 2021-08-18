package com.test.directoryTest;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.github.resource.Directory;
import com.github.resource.FileList;
import com.github.resource.ResourceUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author 康盼Java开发工程师
 */
public class DirectoryTest {

    @Test
    public void test() throws Exception {
        File file = new File("C:\\Users\\康盼Java开发工程师\\Documents\\javacode\\src\\main\\java\\com\\github");
        FileList list = Directory.get(file,".*");
        list.getFiles().forEach(ele -> {
            // System.out.println(ele.getName());
        });
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("bean.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        Element componentScan = root.element("component-scan");
        String scanPath = componentScan.attributeValue("base-package");
        String packagePath = scanPath.replace(".", "/");
        for(URL url : ResourceUtil.getResourcesIterator(packagePath)) {
            System.out.println(url.toString());
        }
    }
}
