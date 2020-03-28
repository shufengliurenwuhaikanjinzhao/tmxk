package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AbstractWeapon
 * @Author Rain
 * @Date 2020/3/12 23:18
 * @Version 1.0
 * @Deacription TODO
 **/
public abstract class AbstractWeapon {
    private static Logger logger = LoggerFactory.getLogger(AbstractWeapon.class);
    abstract void shoot();
}
