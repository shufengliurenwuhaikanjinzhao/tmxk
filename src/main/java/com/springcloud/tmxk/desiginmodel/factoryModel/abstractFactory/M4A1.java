package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName M4A1
 * @Author Rain
 * @Date 2020/3/12 23:09
 * @Version 1.0
 * @Deacription TODO
 **/
public class M4A1 extends AbstractWeapon{
    private static Logger logger = LoggerFactory.getLogger(M4A1.class);

    @Override
    public void shoot(){
        System.out.println("Attack terrorist bases with M4A1.....");
    }
}
