package com.springcloud.tmxk.classLoader;

/**
 * @ClassName MyTest9
 * @Deacription
 * MyTest9 static block
 *  Parent.class static block
 * Child.class static block
 * 4
 * 初始化子类之前必须先初始化父类
 * @Author thl
 * @Date 2019/11/2 15:56
 * @Version 1.0
 **/
public class MyTest9 {
    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}

class Parent {
    static  int a = 3;
    static {
        System.out.println(" Parent.class static block");
    }
}
class Child extends Parent {
    static int b = 4 ;
    static {
        System.out.println("Child.class static block");
    }
}