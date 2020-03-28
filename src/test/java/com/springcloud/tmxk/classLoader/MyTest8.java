package com.springcloud.tmxk.classLoader;

import junit.framework.TestCase;

import java.util.Random;

/**
 * @ClassName MyTest8
 * @Deacription TODO
 * @Author thl
 * @Date 2019/11/2 15:43
 * @Version 1.0
 **/
public class MyTest8 extends TestCase {

    public static void main(String[] args) {
        System.out.println(FinalTest
        .x);
    }

}
class FinalTest{
    //public static final int x =3; // final 不可更改 对于该类是被动引用 在加载过程编译中直接获取值放到引用类的常量池中
    public static final int x = new Random().nextInt(3); //打印FinalTest static block
    //public static int x =3; //打印 FinalTest static block
    static {
        System.out.println("FinalTest static block");
    }
}