package com.github.resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;


/**
 * @author 康盼Java开发工程师
 */
public class HtmlUtil {
    /**
     * 获取表格所有的tr
     *
     * @param html
     * @param tableId
     * @return
     */
    public static Elements getTrs(String html, String tableId) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource(html);
            String urlName = URLDecoder.decode(url.getFile());
            File file = new File(urlName);
            Document doc = Jsoup.parse(file, "UTF-8");
            // 根据 id 获取 table
            Element table = doc.getElementById(tableId);
            // 使用选择器选择该 table 内所有的<tr> <tr/>
            Elements trs = table.select("tr");
            return trs;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
