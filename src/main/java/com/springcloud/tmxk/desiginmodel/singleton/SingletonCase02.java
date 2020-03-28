package com.springcloud.tmxk.desiginmodel.singleton;

/**
 * @ClassName SingletonCase01
 * @Author thl
 * @Date 2020/2/8 11:04
 * @Version 1.0
 * @Deacription 懒汉式1
 **/
public class SingletonCase02 {
    public static void main(String[] args) {
        
    }


}

/**
 * @Author Rain Recommend!!
 * @Date 2020/2/8 11:30
 * @Param
 * @return
 * @Description Slacker Thread-Safety Synchronized method
 * double checked locking
 **/
class Singleton02 {
//    private static Singleton02 instance;
    private static volatile Singleton02 instance;// 指令重排
    private Singleton02(){}

    public static Singleton02 getInstance(){
        if (instance==null){
            synchronized (Singleton02.class){
                if (instance==null) {
                    instance = new Singleton02();
                }
            }
        }
        return instance;
    }

}
