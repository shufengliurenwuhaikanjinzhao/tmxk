package com.springcloud.tmxk.desiginmodel.singleton;

public class Singleton06 {

    private static Singleton06 INSTANCE;

    private Singleton06() {

    }

    /**
     * 不是线程安全的
     * @return
     */
    public static Singleton06 getInstance() {
        if (INSTANCE == null) {
            // 减小同步代码块提高效率 但不可行
            synchronized (Singleton06.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton06();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Singleton06.getInstance().hashCode());
            }).start();
        }
    }

}
