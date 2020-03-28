package com.springcloud.tmxk.desiginmodel.factoryModel.abstractFactory;

import com.springcloud.tmxk.desiginmodel.strategy.rain02.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TestFactory
 * @Author Rain
 * @Date 2020/3/12 23:10
 * @Version 1.0
 * @Deacription TODO
 **/
public class TestFactory extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(TestFactory.class);

    public void testApp() {
        Car car = new Car();
        M4A1 m4A1 = new M4A1();
        ManTou manTou = new ManTou();
        AbstractFactory abstractFactory = new ParadiseFactory();
        AbstractVehicle vehicle = abstractFactory.createVehicle();
        vehicle.go();
        AbstractWeapon weapon = abstractFactory.createWeapon();
        weapon.shoot();
        AbstractFactory hellFactory = new HellFactory();
        AbstractVehicle vehicle1 = hellFactory.createVehicle();
        vehicle1.go();
        AbstractWeapon weapon1 = hellFactory.createWeapon();
        weapon1.shoot();
    }
}
