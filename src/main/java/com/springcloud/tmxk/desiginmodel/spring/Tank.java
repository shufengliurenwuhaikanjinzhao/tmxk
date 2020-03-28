package com.springcloud.tmxk.desiginmodel.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @Author Rain
 * @Date 2020/3/22 13:36
 * @Deacription TODO
 **/
public class Tank {
    public void move() {
        System.out.println("Tank moving calculate...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
