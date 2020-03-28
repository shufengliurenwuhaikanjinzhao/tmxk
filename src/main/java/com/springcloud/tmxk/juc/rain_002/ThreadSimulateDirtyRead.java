package com.springcloud.tmxk.juc.rain_002;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ThreadSimulateDirtyRead
 * @Author Rain
 * @Date 2020/3/7 10:18
 * @Version 1.0
 * @Deacription 对业务写方法枷锁
 * 读方法不加锁
 **/
public class ThreadSimulateDirtyRead {
    private static Logger logger = LoggerFactory.getLogger(ThreadSimulateDirtyRead.class);
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public synchronized void setKey(String key, String value) {
        this.key = key;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = value;
    }

    public String getValue() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        ThreadSimulateDirtyRead dirtyRead = new ThreadSimulateDirtyRead();
        new Thread(() -> {
            dirtyRead.setKey("dirty read", "test value");
        }).start();
        new Thread(() -> {
            System.out.println(dirtyRead.getValue());
        }).start();
    }
}
