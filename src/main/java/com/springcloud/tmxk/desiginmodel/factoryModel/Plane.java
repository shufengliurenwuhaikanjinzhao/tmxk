package com.springcloud.tmxk.desiginmodel.factoryModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Plane
 * @Author Rain
 * @Date 2020/3/12 22:25
 * @Version 1.0
 * @Deacription TODO
 **/
public class Plane implements VehicleInterface {
    private static Logger logger = LoggerFactory.getLogger(Plane.class);

    @Override
    public void go() {
        logger.info("Let's going ......");
    }
}
