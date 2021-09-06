package com.github.jvm;

/**
 * @author 康盼Java开发工程师
 */
public class Main {
    public static void main(String[] args) {
        //-Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
        System.out.println("最大堆大小【-Xmx参数】:"+Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.println("初始堆大小【-Xms参数】:"+Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
        System.out.println("可用内存【无参数】:"+Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
        System.out.println("===============创建5M大小的字符数组====================");
        byte[] b = new byte[5 * 1024 * 1024];
        System.out.println("最大堆大小【-Xmx参数】:"+Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.println("初始堆大小【-Xms参数】:"+Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
        System.out.println("可用内存【无参数】:"+Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
    }
}
