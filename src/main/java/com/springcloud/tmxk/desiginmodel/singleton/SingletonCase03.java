package com.springcloud.tmxk.desiginmodel.singleton;

/**
 * @ClassName SingletonCase01
 * @Author Rain
 * @Date 2020/2/8 11:04
 * @Version 1.0
 * @Deacription Slacker
 **/
public class SingletonCase03 {
    public static void main(String[] args) {
        
    }
}

/** 
 * @Author Rain
 * @Date 2020/2/8 20:53 
 * @Param 
 * @return 
 * @Description Slacker; Thread unsafe; Synchronized code block
 **/
class Singleton03 {
    private static Singleton03 instance;
    private Singleton03(){}

    public static Singleton03 getInstance(){
        if (instance==null){
            // more thread come here
            synchronized (Singleton03.class){
                instance = new Singleton03();
            }
        }
        return instance;
    }
}
