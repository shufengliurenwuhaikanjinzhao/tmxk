package com.springcloud.tmxk.classLoader;

/**
 * @ClassName MyTest12
 * @Deacription
 * 1 反射属于主动引用
 * 2 调用ClassLoader类的loadClass方法加载一个类 并不是对类的主动使用 不会导致类的初始化
 * @Author thl
 * @Date 2019/11/2 16:40
 * @Version 1.0
 **/
public class MyTest12 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = loader.loadClass("com.springcloud.tmxk.classloader.Cl");

        System.out.println(clazz);
        System.out.println("==========");
        clazz = Class.forName("com.springcloud.tmxk.classloader.Cl");
        System.out.println(clazz);
    }
    /**
     * class com.springcloud.tmxk.classloader.Cl
     * ==========
     * Class Cl
     * class com.springcloud.tmxk.classloader.Cl
     **/
}
class Cl {
    static {
        System.out.println("Class Cl");
    }
}