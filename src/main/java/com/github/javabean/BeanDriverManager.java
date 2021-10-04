package com.github.javabean;

import com.alibaba.fastjson.JSON;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static com.github.common.util.StringUtil.getName;

/**
 * @author 康盼Java开发工程师
 */
public class BeanDriverManager implements BeanDriver {
    private final static Object logSync = new Object();
    private static volatile PrintWriter logWriter;

    // class for name 执行 static {} 代码块 只执行一次
    static {
        loadBean();
        PrintStream logStream = new PrintStream(System.out);
        logWriter = new PrintWriter(logStream);
        printAllBean();
    }

    private BeanDriverManager() {

    }

    private static void loadBean() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Map<String, List<String>> map = BeanLoader.loadSpringFactories();
            for (List<String> beanNames : map.values()) {
                for (String beanName : beanNames) {
                    Class<?> beanClass = Class.forName(beanName, false, classLoader);
                    String name = getName(beanClass);
                    Object bean = beanClass.getDeclaredConstructor().newInstance();
                    cache.put(name, bean);
                }

            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Map<String, Object> printAllBean() {
        synchronized (logSync) {
            if (logWriter != null) {
                for (Map.Entry<String, Object> entry : cache.entrySet()) {
                    logWriter.println(entry.getKey() + ": " + JSON.toJSONString(entry.getValue()));
                }
                logWriter.flush();
            }
        }
        return cache;
    }
}
