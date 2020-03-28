package com.springcloud.tmxk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-09
 * @description
 **/
public class AdminServiceDynamicProxy {
    private Object target;
    private InvocationHandler invocationHandler;
    public AdminServiceDynamicProxy(Object target,InvocationHandler invocationHandler){
        this.target = target;
        this.invocationHandler = invocationHandler;
    }

    public Object getPersonProxy() {
        Object obj = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
        return obj;
    }
}