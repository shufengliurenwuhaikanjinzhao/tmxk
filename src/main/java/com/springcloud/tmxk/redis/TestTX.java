package com.springcloud.tmxk.redis;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * @ClassName TestTX
 * @Author thl
 * @Date 2019/12/22 15:40
 * @Version 1.0
 * @Deacription TODO
 **/
public class TestTX extends TestCase {
    public static void main(String[] args) {



    }

    public void testApp1(){
        JedisPool instance = JedisPoolUtil.getInstance();

        try {
            Jedis resource = instance.getResource();
            resource.set("k5","k5");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源

        }



    }
}
