package com.springcloud.tmxk.juc.rain_007;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rain
 * @Date 2020/3/23 23:11
 * @Description
 * Can we invoke sync and async method simultaneously?
 **/
public class RainThread {
    private static Logger logger = LoggerFactory.getLogger(RainThread.class);

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 starting ... ");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end ... ");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName()+" m2 starting ... ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2 end ... ");
    }

    public static void main(String[] args) {
        RainThread thread = new RainThread();
        new Thread(thread::m1,"t1").start();
        new Thread(thread::m2,"t2").start();
    }

}
