package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Ak47
 * @Author Rain
 * @Date 2020/3/12 23:21
 * @Version 1.0
 * @Deacription TODO
 **/
public class Ak47 extends AbstractWeapon {
    private static Logger logger = LoggerFactory.getLogger(Ak47.class);

    @Override
    void shoot() {
        System.out.println("Attack terrorist bases with AK47 ......");
    }
}
