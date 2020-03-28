package com.springcloud.tmxk.mashibing.designpattern.Singleton;

/**
 * @ClassName SingleObject1
 * @Author thl
 * @Date 2019/12/24 23:11
 * @Version 1.0
 * @Deacription
 *  饿汉式的单例模式 线程安全的 基本使用
 **/
public class SingleObject1 {
    //创建 SingleObject 的一个对象 类加载之后初始化一次
    private static SingleObject1 instance = new SingleObject1();

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject1(){}

    //获取唯一可用的对象
    public static SingleObject1 getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
