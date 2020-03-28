package com.springcloud.tmxk.mashibing.juc;

/**
 * @ClassName T001
 * @Author thl
 * @Date 2019/12/24 22:38
 * @Version 1.0
 * @Deacription TODO
 **/
public class T002 {
    private static int count = 10;

    public void m(){
        synchronized (T002.class){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
        }
    }
    public void m2(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
        }
    }
    public synchronized static  void m1(){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
    }
    public static void main(String[] args) {

    }
}
