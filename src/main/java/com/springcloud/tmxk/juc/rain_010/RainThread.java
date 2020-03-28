package com.springcloud.tmxk.juc.rain_010;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/24 22:33
 * @Description TODO
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
            System.out.println("m1 end ......");
        }
    }

    public static void main(String[] args) {
        new T().m1();
    }
}
class T extends RainThread{
    @Override
    synchronized void m1(){
        System.out.println("child starting ...");
        super.m1();
        System.out.println("child end ...");
    }
}
