package com.github;

import com.github.ddd.BaseCore;
import com.github.ddd.SnowflakeIDWorker;
import com.github.env.core.EnvironmentVariableDO;
import com.github.esec.core.BaseRepositoryImpl;
import com.github.mock.UnitTestGeneratorUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;

/**
 * 程序执行入口
 *
 * @author 康盼Java开发工程师
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableScheduling
public class JavaCodeApplication implements ApplicationRunner {

    @Autowired
    private BaseCore baseCore;

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

        String sql = "insert into ENV (ID, ENV_ID, ENV_VALUE, DESCRIPTION) values (?, ?, ?, ?)";
        String id = SnowflakeIDWorker.getInstance().generateId(EnvironmentVariableDO.class);
        Object[] params = new Object[]{id, "OM_PROC_MON_WAIT_HOLD_LOGIC", "0", ""};
        baseCore.insert(sql, params);
    }
}
