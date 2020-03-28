package com.springcloud.tmxk.desiginmodel.proxy.R03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author Rain
 * @Date 2020/3/21 22:51
 * @Deacription
 *   问题：我想记录坦克的移动时间 q1: How to log movement time?
 *   最简单的办法：修改代码，记录时间 The simplest way: modify code and log time
 *   问题2：如果无法改变方法源码呢？ Q2: What if you can not change the source code?
 **/
public class Tank implements Movable {
    /**
     * 模拟坦克移动了一段儿时间  Simulate the tank moved for a while.
     */
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("Tank moving calculate ...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        new Tank().move();
    }
}

interface Movable {
    void move();
}
