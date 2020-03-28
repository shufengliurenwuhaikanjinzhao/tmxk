package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName HellFactory
 * @Author Rain
 * @Date 2020/3/12 23:27
 * @Version 1.0
 * @Deacription 地狱
 **/
public class HellFactory extends AbstractFactory{
    private static Logger logger = LoggerFactory.getLogger(HellFactory.class);

    @Override
    AbstractVehicle createVehicle() {
        return new Walk();
    }

    @Override
    AbstractWeapon createWeapon() {
        return new M4A1();
    }
}
