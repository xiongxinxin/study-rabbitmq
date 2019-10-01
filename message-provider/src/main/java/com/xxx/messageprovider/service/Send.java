package com.xxx.messageprovider.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Simple Queue，简单队列模式，对应message-consumer项目的Recv.java类
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("118.24.67.158");
        try(Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "世界您好！";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发消息：" + message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
