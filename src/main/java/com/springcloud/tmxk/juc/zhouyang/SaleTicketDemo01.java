package com.springcloud.tmxk.juc.zhouyang;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 题目 三个售票员 卖出 30张票
 * 1 在高内聚低耦合的前提下 线程   操作 (对外暴露的调用方法)  资源类
 *       1.1 线程创建一个资源类
 *       1.2
 **/
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        },"B").start();
    }

}
class Ticket{ // 资源类= 实例变量 + 实例方法
    private  int number = 30;
    public synchronized void saleTicket(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+" \t 卖出第 "+ number-- +"张票");
        }
    }


}
