package com.springcloud.tmxk.juc.rain_001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName KlassHaveSynAndGeneralMethod
 * @Author Rain
 * @Date 2020/3/7 9:50
 * @Version 1.0
 * @Description
 **/
public class KlassHaveSynAndGeneralMethod {
    private static Logger logger = LoggerFactory.getLogger(KlassHaveSynAndGeneralMethod.class);

    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " m starting ....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m end ....");
    }

    public void n() {
        System.out.println(Thread.currentThread().getName() + " n starting ....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " n end ....");
    }
    /**
     * @Author Rain
     * @Date 2020/3/7 9:58
     * @Param
     * @return
     * @Description 此案例说明synchronized只针对当前的 方法进行加锁，不会影响到类中其他的普通方法
     **/
    public static void main(String[] args) {
        KlassHaveSynAndGeneralMethod test = new KlassHaveSynAndGeneralMethod();
        new Thread(test::m, "t1").start();
        new Thread(test::n, "t2").start();
    }
}
