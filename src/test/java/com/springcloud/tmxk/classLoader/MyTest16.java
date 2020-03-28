package com.springcloud.tmxk.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName MyTest16
 * @Deacription 自定义类加载器
 * @Author thl
 * @Date 2019/11/2 21:59
 * @Version 1.0
 **/
public class MyTest16 extends ClassLoader{
    private String classLoaderName;
    private final String fileExtention = ".class";
    public MyTest16(String classLoaderName){
        super();//将系统类加载器当做该类加载器的父加载器
        this.classLoaderName= classLoaderName;
    }
    public MyTest16(ClassLoader parent,String classLoaderName){
        super(parent);// 显式指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }
    @Override
    public String toString(){
        return "["+this.classLoaderName+"]";
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] data = this.loadClassData(className);
        return this.defineClass(classLoaderName,data,0,data.length);
    }

    private byte[] loadClassData(String name){
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            this.classLoaderName=this.classLoaderName.replace(".","/");
            is = new FileInputStream(new File(name+this.fileExtention));
            baos = new ByteArrayOutputStream();
            int ch =0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();;
                baos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.springcloud.tmxk.classloader.MyTest5");
        Object o = clazz.newInstance();
        System.out.println(o);
    }

    public static void main(String[] args) throws Exception {
        MyTest16 myTest16 = new MyTest16("loader1");
        test(myTest16);
    }
}
