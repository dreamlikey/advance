package com.wdq.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: wudq
 * @Date: 2018/11/1
 */
@Component
public class MqConsumer {

    @RabbitListener(queues = "queue")
    public void recv(String str) {
        System.err.println("接收到消息："+str);
    }
    @RabbitListener(queues = "queue")
    public void recvObj(User user) {
        System.err.println("接收到消息："+user.toString());
    }

}