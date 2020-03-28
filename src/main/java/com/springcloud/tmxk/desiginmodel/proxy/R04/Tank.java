package com.springcloud.tmxk.desiginmodel.proxy.R04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author Rain
 * @Date 2020/3/21 22:59
 * @Deacription
 *   问题：我想记录坦克的移动时间 q1: How to log movement time?
 *   最简单的办法：修改代码，记录时间 The simplest way: modify code and log time
 *   问题2：如果无法改变方法源码呢？ Q2: What if you can not change the source code?
 *   用继承？ What about extend？
 */
public class Tank implements Movable {

    @Override
    public void move() {
        System.out.println("Tank moving calculate...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Tank2().move();
    }
}

class Tank2 extends Tank {
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        super.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

interface Movable {
    void move();
}
