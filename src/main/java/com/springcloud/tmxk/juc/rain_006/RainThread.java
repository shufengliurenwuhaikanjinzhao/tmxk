package com.springcloud.tmxk.juc.rain_006;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rain
 * @Date 2020/3/23 23:06
 * @Description TODO
 **/
public class RainThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(com.springcloud.tmxk.juc.rain_005.RainThread.class);
    private /*volatile*/ int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        com.springcloud.tmxk.juc.rain_005.RainThread t = new com.springcloud.tmxk.juc.rain_005.RainThread();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD " + i).start();
        }
    }
}