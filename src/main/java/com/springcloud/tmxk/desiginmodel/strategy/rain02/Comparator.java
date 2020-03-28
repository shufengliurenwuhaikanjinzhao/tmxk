package com.springcloud.tmxk.desiginmodel.strategy.rain02;

/**
 * @ClassName Comparable
 * @Author Rain
 * @Date 2020/3/11 23:38
 * @Version 1.0
 * @Deacription TODO
 **/
@FunctionalInterface
public interface Comparator<T> {

    int compareTo(T o1,T o2);
}
