package com.springcloud.tmxk.desiginmodel.singleton;

public class Singleton08 {

    private static Singleton08 INSTANCE;

    private Singleton08() {

    }

    /**
     * 双重检查单例模式
     *
     * @return
     */
    public static Singleton08 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton08.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton08();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton08.getInstance().hashCode());
            }).start();
        }
    }

}
