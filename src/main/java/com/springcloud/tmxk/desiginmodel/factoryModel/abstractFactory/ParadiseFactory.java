package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ParadiseFactory
 * @Author Rain
 * @Date 2020/3/12 23:27
 * @Version 1.0
 * @Deacription 天堂
 **/
public class ParadiseFactory extends AbstractFactory{
    private static Logger logger = LoggerFactory.getLogger(ParadiseFactory.class);

    @Override
    AbstractVehicle createVehicle() {
        return new Car();
    }

    @Override
    AbstractWeapon createWeapon() {
        return new Ak47();
    }
}
