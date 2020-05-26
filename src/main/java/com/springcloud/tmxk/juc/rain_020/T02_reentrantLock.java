package com.springcloud.tmxk.juc.rain_020;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/4/5 16:29
 * @Description TODO
 **/
public class T02_reentrantLock {
    private static Logger logger = LoggerFactory.getLogger(T02_reentrantLock.class);
    synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
    synchronized void m2(){
        System.out.println("m2 ... ");
    }

    public static void main(String[] args){
        T01_reentrantLock01 t01 = new T01_reentrantLock01();
        new Thread(t01::m1).start();
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("TimeUnit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t01::m2).start();
    }


}
