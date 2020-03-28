package com.springcloud.tmxk.desiginmodel.observer.v5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main5 {
    private static Logger logger = LoggerFactory.getLogger(Main5.class);

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }

}

interface Observer {
    void actionOnWakeup();
}

class Child {
    private boolean cry = false;
    private List<Integer> list = Arrays.asList(1,2,3);
    private List<Observer> observers = new ArrayList<Observer>(){
        {
            new Dad();new Mum();new Dog();
        }
    };

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("Waked up! Crying wa wa wa wa......");
        cry = true;
        observers.forEach((observer)->{
            observer.actionOnWakeup();
        });
    }
}

class Dog implements Observer {
    public void bark() {
        System.out.println("Dog wang wang wang ......");
    }

    @Override
    public void actionOnWakeup() {
        bark();
    }
}

class Dad implements Observer {
    public void hold() {
        System.out.println("Dad holding the baby ......");
    }

    @Override
    public void actionOnWakeup() {
        hold();
    }
}

class Mum implements Observer {
    public void feed() {
        System.out.println("Mum feeding the baby ......");
    }

    @Override
    public void actionOnWakeup() {
        feed();
    }
}






