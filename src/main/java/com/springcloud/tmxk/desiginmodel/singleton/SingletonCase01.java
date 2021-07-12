package com.springcloud.tmxk.desiginmodel.singleton;

/**
 * @ClassName SingletonCase01
 * @Author thl
 * @Date 2020/2/8 11:04
 * @Version 1.0
 * @Deacription Slacker
 * 单例模式
 **/
public class SingletonCase01 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Singleton instance = Singleton.getInstance();
                System.out.println(instance);
            },String.valueOf(i)).start();
        }
    }


}

// Thread unsafe; Slacker
class Singleton {
    private static Singleton instance;
    private Singleton(){}

    public static Singleton getInstance(){
        if (instance==null){
            instance = new Singleton();
        }
        return instance;
    }
}

class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {

    }

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();

        System.out.println(m1 == m2);
    }

}

