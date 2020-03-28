package com.springcloud.tmxk.desiginmodel.observer.v4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main4 {
    private static Logger logger = LoggerFactory.getLogger(Main4.class);

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }

}

class Child {
    private boolean cry = false;
    private Dad dad = new Dad();
    private Mum mum = new Mum();
    private Dog dog = new Dog();

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("Waked up! Crying wa wa wa wa......");
        cry = true;
        dad.hold();
        mum.feed();
        dog.bark();
    }
}

class Mum {
    public void feed() {
        System.out.println("Mum feeding the baby ......");
    }
}

class Dad {
    public void hold() {
        System.out.println("Dad holding the baby ......");
    }
}

class Dog {
    public void bark() {
        System.out.println("Dog wang wang wang ......");
    }
}


