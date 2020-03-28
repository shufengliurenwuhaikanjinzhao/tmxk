package com.springcloud.tmxk.classloader.zhouyang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rain
 * @version v1.0.0
 * @date 2019-12-05
 * @description 双亲委派机制
 * 防止篡改代码  任何代码都会加载到跟加载器那里
 **/
public class String {
    private static Logger logger = LoggerFactory.getLogger(String.class);

    static {
        System.out.println("自定义的静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("xxxxxxxxxx");
    }
}
