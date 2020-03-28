package com.springcloud.tmxk.mashibing.designpattern.Singleton;

/**
 * @ClassName SingleObject1
 * @Author thl
 * @Date 2019/12/24 23:11
 * @Version 1.0
 * @Deacription
 *  懒汉式 线程安全
 *  懒加载初始化
 *  线程安全
 *  lazy loading，多线程中使用，但是，效率很低，99% 情况下不需要同步。
 *  优点：第一次调用才初始化，避免内存浪费。
 *  缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 *
 **/
public class SingleObject2 {
    private static SingleObject2 instance;

    private SingleObject2(){}
    //获取唯一可用的对象
    public synchronized static SingleObject2 getInstance(){
        if (instance==null) instance = new SingleObject2();
        return instance;
    }

}
