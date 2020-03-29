package com.springcloud.tmxk.juc.rain_018_001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Rain
 * @Date 2020/3/28 18:17
 * @Description TODO
 **/
public class AtomicInteger_01 {
    private static Logger logger = LoggerFactory.getLogger(AtomicInteger_01.class);
    /*volatile*/ /*int count =0;*/
    AtomicInteger count = new AtomicInteger(0);

    /*synchronized */ void  m(){
        for (int i = 0; i < 10; i++) {
            int i1 = count.incrementAndGet();// count ++;
        }
    }

    public static void main(String[] args) {
        AtomicInteger_01 atomic = new AtomicInteger_01();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(atomic::m,"Thread ->"+i));
        }
        list.forEach((o)->o.start());
        list.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(atomic.count);
    }


}
