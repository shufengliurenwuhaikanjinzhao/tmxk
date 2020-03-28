package com.springcloud.tmxk.classLoader;

import lombok.extern.slf4j.Slf4j;



/**
 * @ClassName MyTest5
 * @Deacription
 * 当一个接口在初始化的时候 并不要求其父接口都完成了初始化
 * 只有在真正使用到父接口的时候（如引用接口中所定义的常量）才会初始化
 * @Author thl
 * @Date 2019/11/2 11:30
 * @Version 1.0
 **/
@Slf4j
public class MyTest5  {

    public void testApp(){
        log.info("输出{}",MyChild5.b);
    }

}

interface MyParent5{
    // 删除父接口 测试不影响使用
    public static final int a =5;
}

interface MyChild5 extends MyParent5{
    public  static final int b = 6;
}