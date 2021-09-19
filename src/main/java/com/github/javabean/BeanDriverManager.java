package com.github.javabean;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author 康盼Java开发工程师
 */
public class BeanDriverManager implements BeanDriver {
    private final static Object logSync = new Object();
    private static volatile PrintWriter logWriter;

    static {
        loadBean();
        PrintStream logStream = new PrintStream(System.out);
        logWriter = new PrintWriter(logStream);
        println("BeanDriverManager initialized");
    }

    private static void loadBean() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        new BeanIterator(BeanDriver.class, classLoader);
    }

    protected static void println(String message) {
        synchronized (logSync) {
            if (logWriter != null) {
                logWriter.println(message);
                logWriter.flush();
            }
        }
    }

    public void printAllBean() {
        cache.forEach((k, v) -> {
            logWriter.println(k + ": " + v);
            logWriter.flush();
        });
    }

}
