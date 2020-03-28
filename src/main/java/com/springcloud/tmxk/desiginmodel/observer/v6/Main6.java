package com.springcloud.tmxk.desiginmodel.observer.v6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main6 {
    private static Logger logger = LoggerFactory.getLogger(Main6.class);

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }

}


class Child {
    private boolean cry = false;
    private List<Integer> list = Arrays.asList(1, 2, 3);
    private List<Observer> observers = new ArrayList<Observer>() {
        {
            new Dad();
            new Mum();
            new Dog();
//            add((e) -> {
//                System.out.println("ppp");
//            });
        }
    };

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("Waked up! Crying wa wa wa wa......");
        cry = true;
        WakeUpEvent event = new WakeUpEvent(System.currentTimeMillis(),"cry");
        observers.forEach((observer) -> {
            observer.actionOnWakeup(event);
        });
    }
}

interface Observer {
    void actionOnWakeup(WakeUpEvent wakeUpEvent);
}

/**
 * @Author Rain
 * @Date 2020/3/15 10:44
 * @Param
 * @return
 * @Description 事件发起者
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
class WakeUpEvent {
    long timestamp;
    String event;
}

class Dog implements Observer {
    public void bark() {
        System.out.println("Dog wang wang wang ......");
    }

    @Override
    public void actionOnWakeup(WakeUpEvent event) {
        bark();
    }
}

class Dad implements Observer {
    public void hold() {
        System.out.println("Dad holding the baby ......");
    }

    @Override
    public void actionOnWakeup(WakeUpEvent event) {
        hold();
    }
}

class Mum implements Observer {
    public void feed() {
        System.out.println("Mum feeding the baby ......");
    }

    @Override
    public void actionOnWakeup(WakeUpEvent event) {
        feed();
    }
}






