package com.springcloud.tmxk.juc.zhouyang;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 *  多线程按照顺序调用 实现A->B->C
 *  三个线程启动 要求如下：
 *  A打印5次 B打印10次 C打印15次
 *  运行10轮
 *   1 高聚低合下 线程操作资源类
 *   2 判断干活 通知
 *   3 多线程交互 防止线程虚假唤醒 （判断只能用while 不能使用if）
 *   4 注意标志位的修改
 *
 **/
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++) {
                shareResource.print15();
            }
        },"C").start();
    }

}

class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void print5(){
        lock.lock();
        try {
            // 1 判断
            while (number !=1 ){
                condition1.await();
            }
            // 2 work
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }
            // 3 notify
            number=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try {
            // 1 判断
            while (number !=2 ){
                condition2.await();
            }
            // 2 work
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }
            // 3 notify
            number=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            // 1 判断
            while (number !=3 ){
                condition3.await();
            }
            // 2 work
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }
            // 3 notify
            number=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}