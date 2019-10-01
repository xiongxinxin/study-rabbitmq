package com.xxx.messageconsumer.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * Simple Queue，简单队列模式，对应message-provider项目的send.java类
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    private final static String HOST = "118.24.67.158";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("等待消息的退出,想要退出消息的监听，请按Ctrl + C");
        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
           String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("得到的消息是：" + message);
        });
        channel.basicConsume(QUEUE_NAME, true, deliverCallback,consumerTag -> {});
    }
}
