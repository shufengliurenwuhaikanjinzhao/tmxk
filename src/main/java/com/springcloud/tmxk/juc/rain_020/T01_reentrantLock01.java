package com.springcloud.tmxk.juc.rain_020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Rain
 * @Date 2020/4/5 16:24
 * @Description reentrant lock
 **/
public class T01_reentrantLock01 {
    private static Logger logger = LoggerFactory.getLogger(T01_reentrantLock01.class);

    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();//synchronized(this)
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2 ... ");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T01_reentrantLock01 t01 = new T01_reentrantLock01();
        new Thread(t01::m1).start();
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("TimeUnit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t01::m2).start();
    }


}
