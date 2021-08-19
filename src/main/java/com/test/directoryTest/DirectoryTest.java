package com.test.directoryTest;

import com.github.resource.ClassScanner;
import com.github.resource.Directory;
import com.github.resource.FileList;
import com.github.resource.ResourceUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;


import java.util.Set;

/**
 * @author 康盼Java开发工程师
 */
public class DirectoryTest {

    @Test
    public void test() {
        ClassScanner classScanner = new ClassScanner("bean.xml");
        Set<Class> set =  classScanner.scan();
        set.stream().forEach(ele -> System.out.println(ele.getSimpleName()));
    }

}
