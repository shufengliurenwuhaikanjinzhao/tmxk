package com.springcloud.tmxk.classLoader;

/**
 * @ClassName MyTest11
 * @Deacription 对父类主动使用  初始化父类
 * @Author thl
 * @Date 2019/11/2 16:32
 * @Version 1.0
 **/
public class MyTest11 {

    public static void main(String[] args) {
        System.out.println(Child3.a);
        Child3.doSomrthing();
    }
    //guess
    /**
     * Parent3 static block
     * 3
     * Doing something
     **/
    //verify
    /**
     * Parent3 static block
     * 3
     * Doing something
     *
     **/
}
class Parent3 {
    static int a = 3;
    static {
        System.out.println("Parent3 static block");
    }
    static void doSomrthing(){
        System.out.println("Doing something");
    }
}
class Child3 extends Parent3 {
    static {
        System.out.println("Child3 static block");
    }
}