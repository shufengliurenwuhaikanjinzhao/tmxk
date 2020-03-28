package com.springcloud.tmxk.desiginmodel.spring.rain;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @Author Rain
 * @Date 2020/3/22 13:37
 * @Deacription
 **/
@Aspect
public class TimeProxy {

    @Before("execution (void com.springcloud.tmxk.desiginmodel.spring.Tank.move())")
    public void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }

    @After("execution (void com.springcloud.tmxk.desiginmodel.spring.Tank.move())")
    public void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }

}
