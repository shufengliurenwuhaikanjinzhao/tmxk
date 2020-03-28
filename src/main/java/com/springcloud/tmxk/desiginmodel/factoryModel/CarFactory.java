package com.springcloud.tmxk.desiginmodel.factoryModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CarFactory
 * @Author Rain
 * @Date 2020/3/12 22:52
 * @Version 1.0
 * @Deacription TODO
 **/
public class CarFactory {
    private static Logger logger = LoggerFactory.getLogger(CarFactory.class);
    public VehicleInterface creatCar(){
        logger.info("Created a car");
        return new Car();
    }
}
