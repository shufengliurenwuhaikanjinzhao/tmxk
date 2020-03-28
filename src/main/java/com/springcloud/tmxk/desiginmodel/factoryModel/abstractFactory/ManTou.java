package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ManTou
 * @Author Rain
 * @Date 2020/3/12 23:10
 * @Version 1.0
 * @Deacription TODO
 **/
public class ManTou extends AbstractFood{
    private static Logger logger = LoggerFactory.getLogger(ManTou.class);

    public void eat(){
        System.out.println("吃起来耐饥");
    }
}
