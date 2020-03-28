package com.springcloud.tmxk.desiginmodel.proxy.R01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author Rain
 * @Date 2020/3/21 22:45
 * @Deacription TODO
 **/
public class Tank implements Movable {
    private static Logger logger = LoggerFactory.getLogger(Tank.class);

    @Override
    public void move() {
        System.out.println("Tank moving calculate...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
interface Movable {
    void move();
}