package com.springcloud.tmxk.juc.rain_000;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_WhatIsThread
 * @Author Rain
 * @Date 2020/3/5 22:51
 * @Version 1.0
 * @Deacription
 **/
public class T01_WhatIsThread {
    private static Logger logger = LoggerFactory.getLogger(T01_WhatIsThread.class);

    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }


}
