package com.springcloud.tmxk.desiginmodel.singleton;

/**
 * @Author Rain
 * @Date 2020/2/9 18:36
 * @Param
 * @return
 * @Description TODO
 **/
public class SingletonCase05 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton05 instance = Singleton05.INSTANCE;
                System.out.println(instance.hashCode());
                System.out.println(instance.get());
            },String.valueOf(i)).start();
        }
    }
}

/**
 * @Author Rain
 * @Date 2020/2/9 18:36
 * @Param
 * @return
 * @Description    Avoid multi-thread problem; Prevent create new object due to deserialization;
 **/
enum Singleton05 {
    INSTANCE; // Property;

    public String get(){
        return "cccc";
    }
}
