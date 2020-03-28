package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import com.springcloud.tmxk.desiginmodel.factoryModel.VehicleInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName Car
 * @Author Rain
 * @Date 2020/3/12 22:24
 * @Version 1.0
 * @Deacription TODO
 **/
public class Car extends AbstractVehicle {
    private static Logger logger = LoggerFactory.getLogger(Car.class);

    @Override
    public void go() {
        logger.info("We are going by car ......");
    }


}
