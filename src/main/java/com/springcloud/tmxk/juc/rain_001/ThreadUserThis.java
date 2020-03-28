package com.springcloud.tmxk.juc.rain_001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @ClassName ThreadUserThis
 * @Author Rain
 * @Date 2020/3/7 10:59
 * @Version 1.0
 * @Deacription TODO
 **/
public class ThreadUserThis {
    private static Logger logger = LoggerFactory.getLogger(ThreadUserThis.class);
    private int count = 10;

    public void rain() {
        synchronized (this) {
            count--;
            logger.info("use this as lock object");
            logger.info(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized void rain1() {// 等同于 synchronized (this) { 与 synchronized (object) 效果一致
        String quote = Pattern.quote(".");//转化为正则 因为 正则应该下位  "\\."

        count--;
        logger.info("use this as lock object");
        logger.info(Thread.currentThread().getName() + " count = " + count);
    }
}

