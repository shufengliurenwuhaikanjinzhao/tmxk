package com.springcloud.tmxk.spring5;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    public static void main(String[] args) {
        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                
                return null;
            }
        };
        function.apply(new HashMap<String, Object>(){{

            put("xx","xx");
            put("yy","yy");
        }});
    }
}
