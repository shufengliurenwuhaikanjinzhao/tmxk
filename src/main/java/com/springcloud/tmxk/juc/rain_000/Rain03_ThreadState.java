package com.springcloud.tmxk.juc.rain_000;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Rain03_ThreadState
 * @Author Rain
 * @Date 2020/3/6 20:19
 * @Version 1.0
 * @Deacription TODO
 **/
public class Rain03_ThreadState {
    private static Logger logger = LoggerFactory.getLogger(Rain03_ThreadState.class);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            logger.info("1Thread State : "+Thread.currentThread().getState());
            logger.info("1Current Thread : " + Thread.currentThread());
        });
        logger.info("2Thread State : " + Thread.currentThread().getState());
        logger.info("2Current Thread : " + Thread.currentThread());
        logger.info("xThread State : " + thread.getState());
        logger.info("xCurrent Thread : " + thread.currentThread());
        thread.start();
        logger.info("3Thread State : " + Thread.currentThread().getState());
        logger.info("3Current Thread : " + Thread.currentThread());
        try {
            thread.join();
            logger.info("yThread State : " + thread.getState());
            logger.info("yCurrent Thread : " + thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("4Thread state=" + Thread.currentThread().getState());
        logger.info("4Current Thread" + Thread.currentThread());
    }
}
