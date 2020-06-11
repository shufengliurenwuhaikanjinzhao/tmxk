package com.springcloud.tmxk.threadPool;

import java.util.concurrent.*;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 *  * 线程池优势：
 *  *  线程池做的工作主要时控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建
 *  *  后启动这些任务，如果线程数量超过了最大的数量，超出数量的线程排队等候，等其他线程执行完毕
 *  *  在从队列中取出任务来执行
 *  *
 *  *  主要特点：
 *  *  线程复用；控制最大并发；管理线程
 *  *
 *  *  第一： 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的小号。
 *  *  第二：提高响应速度。当任务到达时，任务可以不需要等待线程创建就可以执行。
 *  *  第三：提高线程的可管理性。线程时稀缺资源，如果无限制的创建，不仅会小号资源，
 *  *  还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。
 *  *  new ThreadPoolExecutor.AbortPolicy()  暴力拒绝策略
 *  *  new ThreadPoolExecutor.CallerRunsPolicy()  回退到调用者那里
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool3 = Executors.newCachedThreadPool();//自动增加线程
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 11; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t"+"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void threadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//5个受理线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//一个线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();//自动增加线程
        ExecutorService threadPoo4 = Executors.newFixedThreadPool(5, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });
    }
}
