package com.springcloud.tmxk.juc.rain_008;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/24 21:32
 * @Description
 * hei hei
 * Let's call it a day!!
 * call of nature: What's meaning?
 *
 * dirty read
 * In same class, dif sync method has the same lock.
 * There is no influence each other when invoking sync and async method simultaneously.
 **/
public class Account {
    private static Logger logger = LoggerFactory.getLogger(Account.class);

    String name;
    double balance;

    public synchronized void set(String name,double balance){
        this.name=name;
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance=balance;
    }

//    public /*synchronized*/ double getBalance(String name){
//        return this.balance;
//    }
    public synchronized double getBalance(String name){
        return this.balance;
    }
    public static void main(String[] args) {
        Account account = new Account();
        new Thread(()-> account.set("Rain",100.0)).start();
        //System.out.println(account.getBalance("Rain"));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("Rain"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("Rain"));


    }


}
