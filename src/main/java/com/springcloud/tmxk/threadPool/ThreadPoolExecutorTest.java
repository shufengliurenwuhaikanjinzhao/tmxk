package com.springcloud.tmxk.threadPool;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 1 corePoolSize 线程池中的常驻核心线程数
 * 2 maximumPoolSize 线程池中能够容纳同时执行的最大线程数，必须大于等于1
 * 3 keepAliveTime 多余的空闲线程的存活时间 当前池中线程数量超过corePoolSize时，
 *                  当空闲时间达到keepAliveTime时，多余线程会被销毁直到
 *                  只剩下corePoolSize个线程为止
 * 4 unit keepAliveTime的单位
 * 5 workQueue 任务队列，被提交但尚未被执行的任务
 * 6 threadFactory 表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认即可
 * 7 handler 拒绝策略，表示当对列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）
 *              时如何来拒绝请求执行的runnable的策略
 **/
public class ThreadPoolExecutorTest {
    //    public ThreadPoolExecutor(int corePoolSize,
    //                              int maximumPoolSize,
    //                              long keepAliveTime,
    //                              TimeUnit unit,
    //                              BlockingQueue<Runnable> workQueue,
    //                              ThreadFactory threadFactory,
    //                              RejectedExecutionHandler handler) {
    //        if (corePoolSize < 0 ||
    //            maximumPoolSize <= 0 ||
    //            maximumPoolSize < corePoolSize ||
    //            keepAliveTime < 0)
    //            throw new IllegalArgumentException();
    //        if (workQueue == null || threadFactory == null || handler == null)
    //            throw new NullPointerException();
    //        this.corePoolSize = corePoolSize;
    //        this.maximumPoolSize = maximumPoolSize;
    //        this.workQueue = workQueue;
    //        this.keepAliveTime = unit.toNanos(keepAliveTime);
    //        this.threadFactory = threadFactory;
    //        this.handler = handler;
    //    }
}
