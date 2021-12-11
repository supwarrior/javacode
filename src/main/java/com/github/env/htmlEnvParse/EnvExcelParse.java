package com.github.env.htmlEnvParse;

import com.github.resource.HtmlUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 根据HTML文件 环境脚本自动生成
 */
public class EnvExcelParse {

    public static void generateSql() throws Exception {


        Elements elements = HtmlUtil.getTrs("env-01.html", "env-01");
        FileOutputStream fos = new FileOutputStream("env.sql", true);

        for (int i = 0; i < elements.size(); ++i) {
            Element tr = elements.get(i);
            Elements tds = tr.select("td");

            Element evnId = tds.get(1);
            Element newEnvId = tds.get(2);
            Element systemName = tds.get(3);
            Element operation = tds.get(4);
            Element description = tds.get(5);
            Element envValue = tds.get(6);

            String sqlOperation = operation.text();

            EnvModel envModel =  EnvModel.builder()
                    .evnId(evnId.text())
                    .newEvnId(newEnvId.text())
                    .systemName(systemName.text())
                    .operation(sqlOperation)
                    .description(description.text())
                    .envValue(envValue.text().equals("空") ? "" : envValue.text())
                    .build();

            String sql = EnvOperationEnum.getOperationSql(sqlOperation, envModel,false);
            fos.write(sql.getBytes(StandardCharsets.UTF_8));
        }

        Elements elements_2 = HtmlUtil.getTrs("env-02.html", "env-02");
        for (int i = 0; i < elements_2.size(); ++i) {
            Element tr = elements_2.get(i);
            Elements tds = tr.select("td");

            Element evnId = tds.get(0);
            Element newEnvId = tds.get(1);
            Element systemName = tds.get(2);
            Element operation = tds.get(3);
            Element description = tds.get(4);
            Element envValue = tds.get(5);

            String sqlOperation = operation.text();

            EnvModel envModel =  EnvModel.builder()
                    .evnId(evnId.text())
                    .newEvnId(newEnvId.text())
                    .systemName(systemName.text())
                    .operation(sqlOperation)
                    .description(description.text())
                    .envValue(envValue.text().equals("空") ? "" : envValue.text())
                    .build();

            String sql = EnvOperationEnum.getOperationSql(sqlOperation, envModel,false);
            fos.write(sql.getBytes(StandardCharsets.UTF_8));

        }

        Elements elements_3 = HtmlUtil.getTrs("env-03.html", "env-03");
        for (int i = 0; i < elements_3.size(); ++i) {
            Element tr = elements_3.get(i);
            Elements tds = tr.select("td");

            Element evnId = tds.get(1);
            Element newEnvId = tds.get(2);
            Element operation = tds.get(3);
            Element description = tds.get(4);
            Element envValue = tds.get(5);

            String sqlOperation = operation.text();

            EnvModel envModel =  EnvModel.builder()
                    .evnId(evnId.text())
                    .newEvnId(newEnvId.text())
                    .operation(sqlOperation)
                    .description(description.text())
                    .envValue(envValue.text().equals("空") ? "" : envValue.text())
                    .build();

            String sql = EnvOperationEnum.getOperationSql(sqlOperation, envModel,false);
            fos.write(sql.getBytes(StandardCharsets.UTF_8));
        }


        Elements elements_4 = HtmlUtil.getTrs("env-04.html", "env-04");
        for (int i = 0; i < elements_4.size(); ++i) {
            Element tr = elements_4.get(i);
            Elements tds = tr.select("td");

            Element evnId = tds.get(0);
            Element newEnvId = tds.get(1);
            Element operation = tds.get(2);

            String sqlOperation = operation.text();

            EnvModel envModel =  EnvModel.builder()
                    .evnId(evnId.text())
                    .newEvnId(newEnvId.text())
                    .operation(sqlOperation)
                    .build();

            String sql = EnvOperationEnum.getOperationSql(sqlOperation, envModel,true);
            fos.write(sql.getBytes(StandardCharsets.UTF_8));
        }


        fos.close();
    }

    public static void main(String[] args) throws Exception{
        generateSql();
    }
}
