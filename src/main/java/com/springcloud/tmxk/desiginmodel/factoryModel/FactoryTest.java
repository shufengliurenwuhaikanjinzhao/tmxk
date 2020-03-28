package com.springcloud.tmxk.desiginmodel.factoryModel;

import com.springcloud.tmxk.desiginmodel.strategy.rain02.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName FactoryTest
 * @Author Rain
 * @Date 2020/3/12 22:25
 * @Version 1.0
 * @Deacription TODO
 **/
public class FactoryTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(FactoryTest.class);

    public void testApp() {
        Car car = new Car();
        car.go();
        VehicleInterface plane = new SimpleVehicleFactory().creatPlane();
        plane.go();
    }
}
