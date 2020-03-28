package com.springcloud.tmxk.mashibing;

import com.springcloud.tmxk.entity.Person;
import junit.framework.TestCase;

/**
 * @ClassName MaShiBingTest
 * @Author thl
 * @Date 2019/12/21 20:12
 * @Version 1.0
 * @Deacription TODO
 **/
public class MaShiBingTest extends TestCase {

    public void  testApp(){
        System.out.println("zxczxc");
        char[] chars = "123456".toCharArray();
        char[] chars1 = "ASDFFSDA".toCharArray();
        final Object o = new Object();
        new Thread(()->{
            synchronized (o){
                for (char c:chars) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t1").start();

        new Thread(()->{
            synchronized (o){
                for (char c:chars1) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t2").start();
    }


    public void  testApp1(){
        ThreadLocal<Person> threadLocal = new ThreadLocal();
        threadLocal.set(new Person());


    }
    //纤程
    public void  testApp2(){
//        Fiber fiber = new Fiber();


    }
}
