package com.springcloud.tmxk.classLoader;


import org.apache.commons.collections.ListUtils;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @ClassName MyTest14
 * @Deacription 2 获取当前线程上下文的ClassLoader
 * @Author thl
 * @Date 2019/11/2 18:21
 * @Version 1.0
 * <p> <tt>Class</tt> objects for array classes are not created by class
 *  * loaders, but are created automatically as required by the Java runtime.
 *  * The class loader for an array class, as returned by {@link
 *  * Class#getClassLoader()} is the same as the class loader for its element
 *  * type; if the element type is a primitive type, then the array class has no
 *  * class loader.
 *  String[] 的加载器是启动器 是bootsreap
 *  int[] 是原生的 是没有加载器的
 *  数组的类加载器与数组类型的加载器一致  用的时候才会创建类加载器
 **/
public class MyTest14 {
    public static void main(String[] args) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String resource = "com/springcloud/tmxk/classloader/MyTest13.class";

        Enumeration<URL> urls = loader.getResources(resource);
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
        }
        Class<?> myTest14Class = String.class;
        System.out.println(myTest14Class.getClassLoader());
//file:/D:/java/MicroservicesIDEAWS/tmxk/target/test-classes/com/springcloud/tmxk/classloader/MyTest13.class
//sun.misc.Launcher$AppClassLoader@18b4aac2

        //String是由启动类加载 在rt.jar包中 BootstrapClassLoader






    }
}

