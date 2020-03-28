package com.springcloud.tmxk.spring5;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName aop
 * @Author thl
 * @Date 2019/12/27 23:27
 * @Version 1.0
 * @Deacription TODO
 **/
public class aop {
    public void test(){
       new ApplicationContextInitializer() {
            @Override
            public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            }
        };
    }
}
