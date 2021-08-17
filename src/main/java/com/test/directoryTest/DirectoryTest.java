package com.test.directoryTest;

import com.github.resource.Directory;
import com.github.resource.FileList;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class DirectoryTest {

    @Test
    public void test() {
        File file = new File("C:\\Users\\康盼Java开发工程师\\Documents\\javacode\\src\\main\\java\\com\\github\\javabean");
        File[] files = Directory.list(file,".*");
        FileList list = Directory.get(file,".*");
        list.getFiles().forEach(ele -> {
            System.out.println(ele.getName());
        });

    }
}
