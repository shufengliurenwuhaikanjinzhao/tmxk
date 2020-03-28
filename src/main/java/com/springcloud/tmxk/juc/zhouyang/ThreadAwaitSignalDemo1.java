package com.springcloud.tmxk.juc.zhouyang;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 题目： 两个线程，可以操作同一个初始值为0的变量
 * 实现一个线程对该变量加1，一个线程对该变量减一
 * 实现交替 进行10轮变量初始值为0；
 * 1 高内聚低合前提下 线程操作资源类
 * 2 判断干活 通知
 * 3 多线程交互中 必须防止多线程虚假唤醒 （即只能使用while不能使用if）
 * Lock await signal
 **/
public class ThreadAwaitSignalDemo1 {
    public static void main(String[] args) {
        AirConditional2 airConditional2 = new AirConditional2();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
               airConditional2.increment();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                airConditional2.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                airConditional2.increment();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                airConditional2.decrement();
            }
        },"D").start();

    }
}
class AirConditional2{ // 资源类
    private int number =0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            // 1 判断
            while (number !=0 ){
                condition.await();
            }
            // 2 work
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            // 3 notify
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement(){
        lock.lock();
        try {
            // 1. judge
            while (number == 0){
                condition.await();
            }
            // 2 work
            number --;
            System.out.println(Thread.currentThread().getName()+"\t "+number);
            // 3 notify
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }





}
