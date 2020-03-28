package com.springcloud.tmxk.desiginmodel.observer.v3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main3 {
    private static Logger logger = LoggerFactory.getLogger(Main3.class);

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }

}

class Child {
    private boolean cry = false;
    private Dad dad = new Dad();

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("Waked up! Crying wa wa wa wa......");
        cry = true;
        dad.feed();
    }
}

class Dad {
    public void feed() {
        System.out.println("Feeding by dad, kuang chi......");
    }
}



