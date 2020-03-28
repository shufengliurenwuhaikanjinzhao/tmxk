package com.springcloud.tmxk.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description 信号灯
 * 争车位
 * acquire（获取） 当一个线程调用acquire操作是，它要么通过成功获取信号量（信号量减一），
 * 要么一直等下去，知道有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * 信号量主要用于两个目的，一个是用于多个共享资源互斥使用，另一个用于并发线程数的控制。
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 模拟资源类 有三个车位
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();// 抢占资源
                    System.out.println(Thread.currentThread().getName()+"\t"+"抢占了资源");
                    //暂停一会儿
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName()+"\t"+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放资源
                }
            },String.valueOf(i)).start();

        }
    }
}
