package com.wdq.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wudq
 * @Date: 2018/11/1
 */
@Component
public class MqProvider {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpTemplate amqpTemplate;


    public  void send() {
        User user = new User(1,"wdq");
        rabbitTemplate.convertAndSend("queue","你好你好！");
        rabbitTemplate.convertAndSend("queue",user);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消息发送完了！！！");
//        amqpTemplate.convertAndSend("queue","你好你好！");
    }
}

class User implements Serializable {

    private static final long serialVersionUID = -1061746920576970513L;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}