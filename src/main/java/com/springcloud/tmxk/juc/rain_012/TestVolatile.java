package com.springcloud.tmxk.juc.rain_012;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rain
 * @Date 2020/3/28 17:13
 * @Description TODO
 **/
public class TestVolatile {
    private static Logger logger = LoggerFactory.getLogger(TestVolatile.class);

    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                //Thread.yield();
            }
            System.out.println(number);
        }

    }

    public static void main(String[] args) {
        new ReaderThread().start();
        new Thread(()->{
            number=12;
            ready=true;
            while (true);
        }).start();
    }



}
