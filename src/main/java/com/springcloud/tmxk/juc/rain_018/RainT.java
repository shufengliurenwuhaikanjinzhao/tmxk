package com.springcloud.tmxk.juc.rain_018;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rain
 * @Date 2020/3/28 18:13
 * @Description
 * What are you doing now?
 **/
public class RainT {
    private static Logger logger = LoggerFactory.getLogger(RainT.class);

    String s1 = "Hello";
    String s2 = "Hello";

    void s1(){
        synchronized (s1){

        }
    }
    void s2(){
        synchronized (s2){

        }
    }

}
