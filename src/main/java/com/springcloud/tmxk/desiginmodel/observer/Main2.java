package com.springcloud.tmxk.desiginmodel.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Main2
 * @Author Rain
 * @Date 2020/3/15 10:10
 * @Version 1.0
 * @Deacription 等待中 一直等待着
 **/
public class Main2 {
    private static Logger logger = LoggerFactory.getLogger(Main2.class);

    public static void main(String[] args) {
        Child child = new Child();
        while (!child.isCry()){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("observing ......");
        }
    }

}

class Child {
    private boolean cry = false;

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("Waked up! Crying wa wa wa wa......");
        cry = true;
    }
}




