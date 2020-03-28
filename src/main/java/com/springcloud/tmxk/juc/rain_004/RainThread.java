package com.springcloud.tmxk.juc.rain_004;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rain
 * @Date 2020/3/23 22:47
 * @Description TODO
 **/
public class RainThread {
    private static Logger logger = LoggerFactory.getLogger(RainThread.class);

    private static int count = 10;

    /**
     * same as <code>synchronized (RainThread.class)</code>
     **/
    public synchronized static void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    /**
     * How about if write <code>synchronized(this)</code> here?
     **/
    public static void mm() {
        synchronized (RainThread.class) {
            count--;
        }
    }

}
