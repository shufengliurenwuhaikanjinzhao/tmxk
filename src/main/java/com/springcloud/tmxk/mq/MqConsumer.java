package com.springcloud.tmxk.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName MqConsumer
 * @Author thl
 * @Date 2019/12/22 17:50
 * @Version 1.0
 * @Deacription TODO
 **/
public class MqConsumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1 创建ConcentrationFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.232.128");
        connectionFactory.setPort(15672);
        connectionFactory.setVirtualHost("/");

        // 2 通过创建工厂拆功能键连接
        Connection connection = connectionFactory.newConnection();
        // 3 通过connection 创建一个 channel'
        Channel channel = connection.createChannel();

        // 4 生命创建一个队列 "test001" 队列名字
        String queueName = "test001";
        channel.queueDeclare(queueName,true,false,false,null);

        // 5 创建一个消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 6 设置Channel
        channel.basicConsume(queueName,true,queueingConsumer);

        // 7 获取消息
        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
        String body = new String(delivery.getBody());
        System.out.println("消息"+body);
        Envelope envelope = delivery.getEnvelope();
//        envelope.


    }
}
