package com.springcloud.tmxk.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description 循环栅栏
 *      * Each use of the barrier is represented as a generation instance.
 *      栅栏的每次使用都会生成一个实例
 *      * The generation changes whenever the barrier is tripped, or
 *
 *      * is reset. There can be many generations associated with threads
 *      * using the barrier - due to the non-deterministic way the lock
 *      * may be allocated to waiting threads - but only one of these
 *      * can be active at a time (the one to which {@code count} applies)
 *      * and all the rest are either broken or tripped.
 *      * There need not be an active generation if there has been a break
 *      * but no subsequent reset.
 * 做加法
 * 逐次递加
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐龙珠 召唤神龙");
        });

            for (int i = 0; i < 7; i++) {
                final int num=i;
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"\t"+num+"\t"+"召唤。。。");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                },String.valueOf(i)).start();
            }
    }

}
