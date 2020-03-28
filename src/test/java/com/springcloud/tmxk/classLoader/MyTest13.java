package com.springcloud.tmxk.classLoader;

/**
 * @ClassName MyTest13
 * @Deacription 类加载器  1 获取系统类加载器
 * @Author thl
 * @Date 2019/11/2 16:49
 * @Version 1.0
 **/
public class MyTest13 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1 获取系统类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = loader.loadClass("");
        System.out.println(loader);
        while (null!=loader){
            loader = loader.getParent();
            System.out.println(loader);

        }

    }
}


