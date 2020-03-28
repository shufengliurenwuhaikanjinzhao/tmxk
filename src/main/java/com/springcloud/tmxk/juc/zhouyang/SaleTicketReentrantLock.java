package com.springcloud.tmxk.juc.zhouyang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 题目 三个售票员 卖出 30张票
 * 1 在高内聚低耦合的前提下 线程   操作 (对外暴露的调用方法)  资源类
 **/
public class SaleTicketReentrantLock {
    public static void main(String[] args) {
        Ticket1 ticket1 = new Ticket1();
        new Thread(()->{
            for (int i = 0; i < 40 ; i++) {
               ticket1.saleTicket();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40 ; i++) {
               ticket1.saleTicket();
            }
        },"B").start();
    }
}

class Ticket1{
    private int number =30;
    private Lock lock= new ReentrantLock();// 可重入锁
    public void saleTicket(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"\t 卖出第"+number--+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
