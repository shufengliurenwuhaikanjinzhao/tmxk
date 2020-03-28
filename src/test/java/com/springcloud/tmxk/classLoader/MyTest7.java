package com.springcloud.tmxk.classLoader;

import junit.framework.TestCase;

/**
 * @ClassName MyTest7
 * @Deacription 类加载器
 * @Author thl
 * @Date 2019/11/2 15:37
 * @Version 1.0
 **/
public class MyTest7 extends TestCase {

    public void testApp() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());//null
        Class<?> clazz2 = Class.forName("com.springcloud.tmxk.classloader.C");
        System.out.println(clazz2.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2

    }

}
class C {

}
