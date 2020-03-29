package com.springcloud.tmxk.juc.rain_017;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/28 18:05
 * @Description
 *
 **/
public class RainJuc {
    private static Logger logger = LoggerFactory.getLogger(RainJuc.class);

    final Object o = new Object();// It is necessary to add final keyword here
    void m(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        RainJuc juc = new RainJuc();
        new Thread(juc::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }

        Thread thread =new Thread(juc::m,"t2");
        //juc.o = new Object();// we can add final keyword in <code> new Object() </code> to prevent modify object
        thread.start();
    }


}
