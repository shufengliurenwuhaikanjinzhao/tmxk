package com.springcloud.tmxk.mashibing.designpattern.Singleton;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SingleObject3
 * @Author thl
 * @Date 2019/12/24 23:19
 * @Version 1.0
 * @Deacription
 * 饿汉式
 *
 **/
public class SingleObject3 {

    public void test(){
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock1 = new ReentrantLock(true);
    }
}
