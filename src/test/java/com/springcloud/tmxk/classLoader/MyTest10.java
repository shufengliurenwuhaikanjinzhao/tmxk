package com.springcloud.tmxk.classLoader;

/**
 * @ClassName MyTest10
 * @Deacription TODO
 * @Author thl
 * @Date 2019/11/2 16:04
 * @Version 1.0
 **/
public class MyTest10 {
    static {
        System.out.println("MyTest10 static block");
    }
    public static void main(String[] args) {
        Parent2 parent2;
        System.out.println("----------");
        parent2 = new Parent2();
        System.out.println("----------");
        System.out.println(parent2.a);
        System.out.println("----------");
        System.out.println(Child2.b);

    }
    // 猜测
    /** MyTest10 static block
     * Parent2 static block
     * 3
     * Child2 static block
     * 4
     **/
    // 验证
    /**
     * MyTest10 static block
     * ----------
     * Parent2 static block
     * ----------
     * 3
     * ----------
     * Child2 static block
     * 4
     **/
}
class Parent2 {
    static int a = 3 ;
    static {
        System.out.println("Parent2 static block");
    }
}
class Child2 extends Parent2 {
    static int b = 4 ;
    static {
        System.out.println("Child2 static block");
    }
}