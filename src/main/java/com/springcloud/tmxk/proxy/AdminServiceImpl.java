package com.springcloud.tmxk.proxy;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-09
 * @description
 **/
public class AdminServiceImpl implements AdminService{
    public void update() {
        System.out.println("修改管理系统数据");
    }

    public Object find() {
        System.out.println("查看管理系统数据");
        return new Object();
    }
}
