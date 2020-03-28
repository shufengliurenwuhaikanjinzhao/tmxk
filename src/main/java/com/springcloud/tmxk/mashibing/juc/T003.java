package com.springcloud.tmxk.mashibing.juc;

/**
 * @ClassName T001
 * @Author thl
 * @Date 2019/12/24 22:38
 * @Version 1.0
 * @Deacription
 * 同步方法可以调用非同步方法
 *
 **/
public class T003 {
    private static int count = 10;
    public synchronized void m1(){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
            m();
    }
    public void m(){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
    }
    public static void main(String[] args) {

    }
}
