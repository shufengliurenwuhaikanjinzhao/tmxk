package com.springcloud.tmxk.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 *  多线程 第三种获取多线程的方法
 *  Runnable 与 Callable区别
 *  1 落地方法不一样
 *  2 是否异常
 *  3 是否返回值
 *
 *  get方法一般放在最后一行
 *
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(new MyThread1());
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); //直接服用第一次的值 不会再去计算

        System.out.println(Thread.currentThread().getName()+"\t 主线程走完");
        System.out.println(futureTask.get());
    }
}
class MyThread implements Runnable{
    @Override
    public void run(){

    }
}
class MyThread1 implements Callable<Integer>{
    @Override
    public Integer call() throws InterruptedException {
        System.out.println("==Callable==");
        // 暂停一会
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}