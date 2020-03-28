package com.springcloud.tmxk.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @ClassName RedisTest
 * @Author thl
 * @Date 2019/12/21 14:05
 * @Version 1.0
 * @Deacription TODO
 **/
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.232.128", 6379);
        //jedis.set("k1","v1");
        String k1 = jedis.get("k1");

        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());

        System.out.println(k1);
        System.out.println(jedis.ping());


    }
}
