package com.springcloud.tmxk.desiginmodel.factoryModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName SimpleVehicleFactory
 * @Author Rain
 * @Date 2020/3/12 22:34
 * @Version 1.0
 * @Deacription TODO
 **/
public class SimpleVehicleFactory {
    private static Logger logger = LoggerFactory.getLogger(SimpleVehicleFactory.class);

    public Car creatCar() {
        return new Car();
    }

    public Plane creatPlane() {
        return new Plane();
    }
}
