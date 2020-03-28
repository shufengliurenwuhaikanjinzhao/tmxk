package com.springcloud.tmxk.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName MqProducer
 * @Author thl
 * @Date 2019/12/22 17:50
 * @Version 1.0
 * @Deacription TODO
 **/
public class MqProducer {
    public static void test(){
//        HttpClien
    }


    public static void main(String[] args) throws IOException, TimeoutException {
        test();
        // 1 创建ConcentrationFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.232.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2 通过创建工厂拆功能键连接
        Connection connection = connectionFactory.newConnection();
        // 3 create a chaanel by connection
        Channel channel = connection.createChannel();
        // 4 send message by channel
        for (int i = 0; i < 5; i++) {
            String msg="hhhhhhhhh"+i;
            channel.basicPublish("","test001",null,msg.getBytes());
        }


        // 5 close connection
        channel.close();
        connection.close();


    }
}
