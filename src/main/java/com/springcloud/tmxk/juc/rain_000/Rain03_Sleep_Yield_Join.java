package com.springcloud.tmxk.juc.rain_000;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Rain03_Sleep_Yield_Join
 * @Author Rain
 * @Date 2020/3/5 23:03
 * @Version 1.0
 * @Deacription
 **/
public class Rain03_Sleep_Yield_Join {
    private static Logger logger = LoggerFactory.getLogger(Rain03_Sleep_Yield_Join.class);

    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                logger.info("A=={}", i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * @return
     * @Author Rain
     * @Date 2020/3/5 23:15
     * @Param
     * @Description 让出一下cpu（返回到就绪状态）  至于其他线程是否抢到无所谓
     **/
    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                logger.info("B=={}", i);
                if (i % 10 == 0) Thread.yield();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                logger.info("C=={}", i);
                if (i % 10 == 0) Thread.yield();
            }
        }).start();
    }

    /**
     * @return
     * @Author Rain
     * @Date 2020/3/5 23:14
     * @Param
     * @Description T1线程的调用中间插入调用t2线程，当t2线程结束之后才会再去调用t1线程
     **/
    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                logger.info("D=={}", i);
                try {
                    Thread.sleep(500);
//                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                logger.info("E=={}", 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();

    }
}
