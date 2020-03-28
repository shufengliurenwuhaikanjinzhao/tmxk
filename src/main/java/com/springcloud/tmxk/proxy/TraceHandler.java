package com.springcloud.tmxk.proxy;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-09
 * @description
 **/
public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.print(target);
        System.out.print("." + method.getName() + "(");

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i<args.length - 1){
                    System.out.print(", ");
                }
            }
        }

        System.out.println(")");

        return method.invoke(target, args);
    }
}
