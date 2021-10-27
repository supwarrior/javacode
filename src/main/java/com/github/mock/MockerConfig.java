package com.github.mock;

import com.github.common.cons.Constant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
public class MockerConfig {

    public static Configuration cfg;

    public static void generatorUnitTest(File testFile,Mocker mocker) throws Exception {
        if (cfg == null) {
            cfg = getConfiguration();
        }
        Map<String, Object> data = new HashMap<>(2);
        data.put("mocker", mocker);
        Template template = cfg.getTemplate("unit-test-template.ftl");
        if (!testFile.getParentFile().exists() && !testFile.getParentFile().mkdirs()) {
            return;
        }
        if (!testFile.exists()) {
            template.process(data,new FileWriter(testFile));
        }
    }

    /**
     * 生成freemarker引擎配置
     *
     * @return
     * @throws IOException
     */
    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //模板文件
        File file = new File(Constant.BASEDIR + Constant.CONFIG_PATH);
        cfg.setDirectoryForTemplateLoading(file);
        //设置文件编码
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

}
