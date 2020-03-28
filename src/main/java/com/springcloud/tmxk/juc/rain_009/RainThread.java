package com.springcloud.tmxk.juc.rain_009;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/24 22:23
 * @Description
 * synchronized is reentrant lock:
 * one sync method can invoke another sync method, that's to say, if the first thread had the object lock,
 * and you'll still get it when apply for the object lock again.
 **/
public class RainThread {
    private static Logger logger = LoggerFactory.getLogger(RainThread.class);
     void m1(){
         synchronized(this){
             System.out.println("m1 starting .....");
             try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             m2();
             System.out.println("m1 end ......");
         }
    }

    synchronized void m2() {
        System.out.println("m2 starting .....");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end ......");
    }

    public static void main(String[] args) {
        RainThread thread = new RainThread();
        thread.m1();
    }

}
