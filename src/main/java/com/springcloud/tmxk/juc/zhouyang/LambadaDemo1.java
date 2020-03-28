package com.springcloud.tmxk.juc.zhouyang;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 1 拷贝小括号 写死右箭头 落地大括号
 * 2 @FunctionalInterface //函数式接口
 * 3 default
 * 4 静态方实现
 **/
public class LambadaDemo1 {
    public static void main(String[] args) {
        Thl thl =()->{
            System.out.println("嘿嘿嗨");
        };
        thl.sayHello();
        Thl1 thl1 =(x,y)->{
            return x+y;
        };
        System.out.println(thl1.add(3,5));
        System.out.println(thl1.div(10,5));
        System.out.println(Thl1.mv(10,8));
    }
}
@FunctionalInterface //函数式接口
interface Thl{
    void sayHello();
}
interface Thl1{
    Integer add(int x, int y);
    static int mv(int x, int y){
        return x*y;
    }

    default  int div(int x, int y){
        System.out.println(" x y\t"+x+"\t"+y);
        return x/y;
    }
    default  int div1(int x, int y){
        System.out.println(" x y"+x+"\t"+y);
        return x/y;
    }
}