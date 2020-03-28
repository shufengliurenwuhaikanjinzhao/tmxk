package com.springcloud.tmxk.desiginmodel;

/**
 * @ClassName Test
 * @Author thl
 * @Date 2020/2/2 22:02
 * @Version 1.0
 * @Deacription
 * 1 设计模式
 * 23 中设计模式
 * 设计模式：
 *      代码重用性
 *      可读性
 *      可扩展性（便于增加新的功能 即可维护性）
 *      可靠性（增加新的功能 对原有的功能没有影响）
 *      高内聚 低耦合
 *  七大原则：
 *      1 单一职责
 *      2 接口隔离
 *      3 依赖倒换（倒置）
 *      4 里氏替换
 *      5 开闭原则
 *      6 迪米特原则
 *      7 合成复用
 *  设计模式 ->面向对象分析和设计的精要（OOA/D） Scott Mayers
 **/
public class Test
{

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.maxMemory()/1024/1024/1024);
    }

}
