package com.springcloud.tmxk.juc.rain_001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName T
 * @Author Rain
 * @Date 2020/3/6 21:04
 * @Version 1.0
 * @Deacription synchronized 锁升级现象  可重入锁
 **/
public class T1 {
    private static Logger logger = LoggerFactory.getLogger(T1.class);
    private int count = 10;
    private Object object = new Object();

    public void m() {
        synchronized (object) {//任何线程执行下面代码 必须获取到object的锁
            count--;
            logger.info(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void n() {//锁定当前类
        count--;
        logger.info(Thread.currentThread().getName() + " count = " + count);
    }

    public void nn() {
        synchronized (T1.class) {//锁定当前类
            count--;
            logger.info(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
