package com.springcloud.tmxk.juc.rain_000;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Rain02_HowToCreateThread
 * @Author Rain
 * @Date 2020/3/5 23:00
 * @Version 1.0
 * @Deacription How to create thread in your project？
 * Extend Thread
 * Implement Runnable
 * Executor.newCachedThread(Thread pool)
 **/
public class Rain02_HowToCreateThread {
    private static Logger logger = LoggerFactory.getLogger(Rain02_HowToCreateThread.class);

    static class MyThread extends Thread{
        @Override
        public void run() {
            logger.info("Hello Rain, Mythread");
        }
    }
    static class MyRun implements Runnable{

        @Override
        public void run() {
            logger.info("Hello Rain, MyRun");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            logger.info("Hello Rain, Lmbda");
        }).start();
    }
}
