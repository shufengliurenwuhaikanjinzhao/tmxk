package com.springcloud.tmxk.juc.rain_011;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/24 22:42
 * @Description
 * Thead will release lock if encounter a exception, and then other thread will hold the lock.
 **/
public class RainThread {
    private static Logger logger = LoggerFactory.getLogger(RainThread.class);
    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");

        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int e = count / 0;// Avoid releasing lock unless catch the exception on purpose.
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        RainThread thread = new RainThread();
        Runnable runnable = () -> thread.m();
        runnable = new Runnable() {
            @Override
            public void run() {
                thread.m();
            }
        };

        new Thread(() -> {
            thread.m();
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(runnable, "t2").start();
    }
}
