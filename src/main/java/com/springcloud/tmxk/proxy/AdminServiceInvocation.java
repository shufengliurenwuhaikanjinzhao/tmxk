package com.springcloud.tmxk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-09
 * @description
 **/
public class AdminServiceInvocation  implements InvocationHandler {

    private Object target;

    public AdminServiceInvocation(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("判断用户是否有权限进行操作");
        Object obj = method.invoke(target);
        System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
        return obj;
    }
}