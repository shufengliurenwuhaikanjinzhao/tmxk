package com.springcloud.tmxk.desiginmodel.singleton;

/**
 * @ClassName SingletonCase04
 * @Author Rain
 * @Date 2020/2/8 11:04
 * @Version 1.0
 * @Deacription
 **/
public class SingletonCase04 {
    public static void main(String[] args) {
        System.out.printf("xxx");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton04 instance = Singleton04.getInstance();
                System.out.println(instance);
            },String.valueOf(i)).start();
        }
    }
}

/**
 * @Author Rain
 * @Date 2020/2/8 21:32
 * @Param
 * @return
 * @Description Static inner class case; perfect
 *
 **/
class Singleton04 {
    private static Singleton04 instance;
    private Singleton04(){
        System.out.printf("yyy");
    }
    //
    private static class SingletonInstance{
        static {
            System.out.println("执行一次");
        }
        private static final Singleton04 INSTANCE = new Singleton04();
    }
    public static Singleton04 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
