package com.springcloud.tmxk.desiginmodel.proxy.R06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author Rain
 * @Date 2020/3/21 23:07
 * @Deacription TODO
/**
 * 问题：我想记录坦克的移动时间
 * 最简单的办法：修改代码，记录时间
 * 问题2：如果无法改变方法源码呢？
 * 用继承？
 * v05:使用代理
 * v06:代理有各种类型 There are kinds of type about proxy.
 * 问题：如何实现代理的各种组合？继承？Decorator? How to implement dif component about proxy? Extend?
 */
public class Tank implements Movable {
    @Override
    public void move() {
        System.out.println("Tank moving calculate  ...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TankTimeProxy().move();
    }
}

class TankTimeProxy implements Movable {
    Tank tank;
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class TankLogProxy implements Movable {
    Tank tank;
    @Override
    public void move() {
        System.out.println("start moving...");
        tank.move();
        System.out.println("stopped!");
    }
}

interface Movable {
    void move();
}
