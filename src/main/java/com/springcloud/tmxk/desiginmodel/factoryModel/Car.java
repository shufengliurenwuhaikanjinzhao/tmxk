package com.springcloud.tmxk.desiginmodel.factoryModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName Car
 * @Author Rain
 * @Date 2020/3/12 22:24
 * @Version 1.0
 * @Deacription TODO
 **/
@Component
public class Car implements VehicleInterface {
    private static Logger logger = LoggerFactory.getLogger(Car.class);

    @Override
    public void go() {
        logger.info("{} Let's going ......",Car.class.getMethods()[0]);
    }
}
