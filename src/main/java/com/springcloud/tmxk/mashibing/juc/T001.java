package com.springcloud.tmxk.mashibing.juc;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName T001
 * @Author thl
 * @Date 2019/12/24 22:38
 * @Version 1.0
 * @Deacription TODO
 **/
public class T001 {

    private int count = 10;
    private Object o = new Object();



    public void m(){
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
        }
    }
}
