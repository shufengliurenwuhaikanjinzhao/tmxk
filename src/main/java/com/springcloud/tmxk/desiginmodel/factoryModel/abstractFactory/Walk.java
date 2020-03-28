package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import com.springcloud.tmxk.desiginmodel.factoryModel.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Walk
 * @Author Rain
 * @Date 2020/3/12 23:31
 * @Version 1.0
 * @Deacription TODO
 **/
public class Walk extends AbstractVehicle {
    private static Logger logger = LoggerFactory.getLogger(Walk.class);
    @Override
    public void go() {
        logger.info("Let's go to on foot ......");
    }
}
