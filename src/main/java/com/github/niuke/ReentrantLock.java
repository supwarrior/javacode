package com.github.niuke;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;


/**
 * @author 康盼Java开发工程师
 */
public class ReentrantLock {

    private final Sync sync;

    public ReentrantLock() {
        this.sync = new Sync();
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        public boolean tryAcquire(int acquire) {
            if (compareAndSetState(0, acquire)) {
                // 设置当前拥有独占访问权限的线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int release) {
            if (getState() == 0) {
                return false;
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    public void lock() {
        sync.acquire(1);
    }

    public void unLock() {
        sync.release(1);
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t1 加锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            } finally {
                System.out.println("t1 释放锁");
                reentrantLock.unLock();
            }
        });
        Thread t2 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t2 加锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            } finally {
                System.out.println("t2 释放锁");
                reentrantLock.unLock();
            }
        });
        Thread t3 = new Thread(() -> {
            reentrantLock.lock();
            System.out.println("t3 加锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            } finally {
                System.out.println("t3 释放锁");
                reentrantLock.unLock();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
