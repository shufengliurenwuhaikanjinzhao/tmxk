package com.springcloud.tmxk.classloader.zhouyang;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description 三种加载机制
 **/
public class Hello022 {
    public static void main(java.lang.String[] args) {
        Object o = new Object();
        System.out.println(o.getClass().getClassLoader());

        Hello022 hello022 = new Hello022();
        System.out.println(hello022.getClass().getClassLoader());
        System.out.println(hello022.getClass().getClassLoader().getParent().getParent());
        System.out.println(hello022.getClass().getClassLoader().getParent().getParent().getParent());

    }
}
