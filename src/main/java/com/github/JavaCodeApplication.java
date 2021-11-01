package com.github;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import com.github.mock.UnitTestGeneratorUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.InputStream;

/**
 * 程序执行入口
 *
 * @author 康盼Java开发工程师
 */
@SpringBootApplication
@EnableDiscoveryClient
public class JavaCodeApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(JavaCodeApplication.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("ut.xml");
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        Element componentScan = root.element("testPackage");
        if (componentScan != null) {
            String scanPaths = componentScan.attributeValue("name");
            String[] packagePaths = scanPaths.split(",");
            for (String scanPath : packagePaths) {
                UnitTestGeneratorUtil.generatorUnitTest(scanPath);
            }
        }
    }
}
