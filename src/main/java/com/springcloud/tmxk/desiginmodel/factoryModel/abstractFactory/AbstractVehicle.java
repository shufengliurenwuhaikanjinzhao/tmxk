package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AbstractVehicle
 * @Author Rain
 * @Date 2020/3/12 23:17
 * @Version 1.0
 * @Deacription TODO
 **/
public abstract class AbstractVehicle {
    private static Logger logger = LoggerFactory.getLogger(AbstractVehicle.class);

    abstract void go();
}
