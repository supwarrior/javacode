package com.github.demo.mainfun.singleton;

public class Singleton {

    private volatile static Singleton singleton;
    private Singleton(){

    }
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    @Override
    public String toString() {
        return "Singleton{}";
    }
}
