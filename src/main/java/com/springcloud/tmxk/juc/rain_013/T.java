package com.springcloud.tmxk.juc.rain_013;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rain
 * @Date 2020/3/28 17:26
 * @Description
 **/
public class T {
    private static Logger logger = LoggerFactory.getLogger(T.class);

    volatile int count = 0; // It is not guarantee atomicity

    void m() {
        for (int i = 0; i < 1000; i++) count++;
    }

    int count1 = 0;

    synchronized void m1() {
        for (int i = 0; i < 1000; i++) count++;
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread->"+i));
        }
        threads.forEach((o)->o.start());
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);

    }
}
