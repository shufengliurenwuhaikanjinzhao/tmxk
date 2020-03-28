package com.springcloud.tmxk.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisPool
 * @Author thl
 * @Date 2019/12/22 16:03
 * @Version 1.0
 * @Deacription TODO
 **/
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool=null;

    private JedisPoolUtil(){}
    public static JedisPool getInstance(){
        if (null==jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();

                    //jedisPool = new JedisPool("192.168.232.128",6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis){

        if (null!=jedis){
            //释放资源
            //jedisPool.re
        }
    }

    public void test(){
        JedisPool jedisPool = JedisPoolUtil.jedisPool;
        JedisPool instance = JedisPoolUtil.getInstance();


    }
}
