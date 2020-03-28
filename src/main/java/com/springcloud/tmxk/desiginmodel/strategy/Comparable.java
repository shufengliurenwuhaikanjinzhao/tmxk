package com.springcloud.tmxk.desiginmodel.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Comparable
 * @Author Rain
 * @Date 2020/3/11 23:01
 * @Version 1.0
 * @Deacription TODO
 **/
public interface Comparable<T> {

    int compareTo(T o);
}
