package com.springcloud.tmxk.redis.redistransition;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

/**
 * @ClassName RedisTest
 * @Author thl
 * @Date 2019/12/25 22:33
 * @Version 1.0
 * @Deacription TODO
 **/
public class RedisTest {
    private StringRedisTemplate redisTemplate = new StringRedisTemplate();
//    private Redisson


    public void test(){
        String lockKey ="lock";
        Integer count = Integer.valueOf(Objects.requireNonNull(redisTemplate.opsForValue().get("count")));
        try {

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }



    }
}
