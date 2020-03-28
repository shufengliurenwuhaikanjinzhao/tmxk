package com.springcloud.tmxk.juc.zhouyang;

/**
 * @ClassName ThreadWaitNotifyDemo
 * @Deacription
 * 题目：两个线程，可以操作同一个初始值为0的变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替 进行10轮，变量初始值为0；
 * 1 高聚低合前提下 线程操作资源类
 * 2 判断 干活 通知
 * 3 多线程交互中  必须防止多线程的虚假唤醒，也即（判断只用while 不能使用if）
 * @Author thl
 * @Date 2019/11/3 7:55
 * @Version 1.0
 **/
public class ThreadWaitNotifyDemo1 {
    public static void main(String[] args) {
        AirConditioner1 airConditioner = new AirConditioner1();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread B").start();

        /**
         * 增加到4个线程的时候就会出错
         * 采用while循环判断就可以
         **/
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread D").start();
    }
}

class AirConditioner1{// 资源类
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        // 1 判断
        while (number != 0){
            this.wait();
        }
        // 2 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+ number);
        // 3 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 1 判断
        while (number == 0){
            this.wait();
        }
        // 2 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+ number);
        // 3 通知
        this.notifyAll();
    }
}

