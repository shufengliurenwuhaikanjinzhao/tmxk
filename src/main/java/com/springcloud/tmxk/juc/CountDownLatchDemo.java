package com.springcloud.tmxk.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 做减法
 * countDown 执行一次  递减一个
 * await 等待为0时唤醒主线程
 *
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 跑了");
            },String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待为0时唤醒主线程
        System.out.println(Thread.currentThread().getName()+"\t 关门放狗");
    }
    private static void xxx() {
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"逃跑了");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"关门打狗");
    }

}
