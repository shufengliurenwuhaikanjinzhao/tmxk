package com.springcloud.tmxk.desiginmodel.singleton;


/**
 * 解决线程问题还可以防止反序列化
 */
public enum Singleton09 {

    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton09.INSTANCE.hashCode());
            }).start();
        }
    }

}
