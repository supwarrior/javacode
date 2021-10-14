package com.github.resource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author 康盼Java开发工程师
 */
public class PropertyUtil {

    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private static final Locale EN = new Locale("en", "US");

    private static final Locale ZH = new Locale("zh", "CN");

    private static final Map<String, String> cache = new HashMap<>();

    /**
     * Java读取 properties 文件
     *
     * @param fileName eg:i18n_zh_CN.properties
     * @param keyName  key
     * @return
     * @throws IOException
     */
    public static String getProperty(String fileName, String keyName) throws IOException {
        if (cache.containsKey(keyName)) {
            return cache.get(keyName);
        }
        InputStream in = classLoader.getResourceAsStream(fileName);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Properties properties = new Properties();
        properties.load(bf);
        Enumeration<?> keys = properties.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = properties.getProperty(key);
            cache.put(key, value);
        }
        return cache.getOrDefault(keyName, "");
    }

    /**
     * getPropertyNoCache
     *
     * @param fileName
     * @param keyName
     * @return
     * @throws IOException
     */
    public static String getPropertyNoCache(String fileName, String keyName) throws IOException {
        InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
        BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Properties properties = new Properties();
        properties.load(bf);
        return properties.getProperty(keyName);
    }

    /**
     * getPropertyByUrl
     *
     * @param fileName
     * @param keyName
     * @return
     * @throws IOException
     */
    public static String getPropertyByUrl(String fileName, String keyName) throws IOException {
        URL url = classLoader.getResource(fileName);
        if (url != null) {
            String file = URLDecoder.decode(url.getFile());
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            Properties properties = new Properties();
            properties.load(bf);
            return properties.getProperty(keyName);
        }
        return "";
    }

    /**
     * getPropertyByResourceBundle
     *
     * @param fileName eg:i18n
     * @param keyName
     * @return
     * @throws IOException
     */
    public static String getProperty(String fileName, String keyName, String language) throws IOException {
        if (cache.containsKey(keyName)) {
            return cache.get(keyName);
        }
        return getPropertyByResourceBundle(fileName, language).getOrDefault(keyName, "");
    }

    public static Map<String, String> getPropertyByResourceBundle(String fileName, String language) throws IOException {
        ResourceBundle resourceBundle;
        if (language.equals(EN.getLanguage())) {
            resourceBundle = ResourceBundle.getBundle(fileName, EN);
        } else {
            resourceBundle = ResourceBundle.getBundle(fileName, ZH);
        }
        Enumeration<String> enumeration = resourceBundle.getKeys();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = resourceBundle.getString(key);
            cache.put(key.toUpperCase(), value);
        }
        return cache;
    }

    /**
     * prefix of ascii string of native character
     */
    private static String PREFIX = "\\u";

    /**
     * 将 ascii 码转为中文字符
     *
     * @param str
     * @return
     */
    public static String ascii2Native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf(PREFIX);
        while (index != -1) {
            sb.append(str, begin, index);
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf(PREFIX, begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    private static char ascii2Char(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must be 6 character.");
        }
        if (!PREFIX.equals(str.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must start with \\u");
        }
        String tmp = str.substring(2, 4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }

    /**
     * 将中文字符转为 ascii 码
     *
     * @param str
     * @return
     */
    public static String native2Ascii(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(char2Ascii(chars[i]));
        }
        return sb.toString();
    }

    private static String char2Ascii(char c) {
        if (c > 255) {
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX);
            int code = (c >> 8);
            String tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            code = (c & 0xFF);
            tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            return sb.toString();
        } else {
            return Character.toString(c);
        }
    }
}


