package com.springcloud.tmxk.juc.rain_016;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/28 17:57
 * @Description TODO
 **/
public class LockOptimize {
    private static Logger logger = LoggerFactory.getLogger(LockOptimize.class);

    int count = 0;

    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // you can add sync in the code, rather add in method
        count++;

        //TODO ANYTHING
    }

    void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // you can add sync in the code, rather add in method
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
