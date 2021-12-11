package com.github.demo.mainfun.singleton;

public class SingletonMain implements Runnable {

    private Singleton singleton = null;

    public  Singleton getSingleton() {
        return singleton;
    }

    public void setSingleton(Singleton singleton) {
        this.singleton = singleton;
    }

    @Override
    public void run() {
        this.singleton = Singleton.getInstance();
    }

    public static void main(String[] args) throws Exception{
        SingletonMain singletonMain_1 = new SingletonMain();
        SingletonMain singletonMain_2 = new SingletonMain();
        Thread thread_1 = new Thread(singletonMain_1);
        Thread thread_2 = new Thread(singletonMain_2);
        thread_1.start();

        thread_2.start();
//        Thread.sleep(3000L);

        Singleton singleton_1 = singletonMain_1.getSingleton();
        Singleton singleton_2 = singletonMain_2.getSingleton();

        System.out.println(singleton_1 == singleton_2);
    }
}
