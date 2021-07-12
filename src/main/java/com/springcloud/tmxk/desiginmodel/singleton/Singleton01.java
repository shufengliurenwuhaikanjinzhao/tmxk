package com.springcloud.tmxk.desiginmodel.singleton;

/**
 * 静态内部类
 * JVM保证单例
 * 加载外部类时 不会加载内部类 这样可以实现懒加载
 *
 *
 */
public class Singleton01 {

    private Singleton01() {

    }

    private static class Mgr001 {
        private final static Singleton01 INSTANCE = new Singleton01();
    }

    public static Singleton01 getInstance() {
        return Mgr001.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton01.getInstance().hashCode());
            }).start();
        }
    }

}
