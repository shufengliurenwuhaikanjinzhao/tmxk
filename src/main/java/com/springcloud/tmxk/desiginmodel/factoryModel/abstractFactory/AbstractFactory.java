package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AbstractFactory
 * @Author Rain
 * @Date 2020/3/12 23:15
 * @Version 1.0
 * @Deacription TODO
 **/
public abstract class AbstractFactory {
    private static Logger logger = LoggerFactory.getLogger(AbstractFactory.class);
    abstract AbstractVehicle createVehicle();
    abstract AbstractWeapon createWeapon();
}
