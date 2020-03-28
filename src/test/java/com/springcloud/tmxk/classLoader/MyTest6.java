package com.springcloud.tmxk.classLoader;

import junit.framework.TestCase;

/**
 * @ClassName MyTest6
 * @Deacription TODO
 * @Author thl
 * @Date 2019/11/2 11:42
 * @Version 1.0
 **/

public class MyTest6 extends TestCase {

    public void testApp(){
        Singleton singleton = Singleton.getSingleton();
        System.out.println("counter1: " +Singleton.counter1);
        System.out.println("counter2: " +Singleton.counter2);
        System.out.println("counter3: " +Singleton.counter3);

    }
}

class  Singleton{
    public static int counter1;
    //public static int counter1=1;
    public static int counter2 = 0;
    public static Singleton singleton = new Singleton();
    private Singleton() {
        counter1++;
        counter2++;
        counter3++;
        System.out.println(counter1);
        System.out.println(counter2);
        System.out.println(counter3);
    }
    public static int counter3 = 0;
    public static Singleton getSingleton(){
        return singleton;
    }
}
