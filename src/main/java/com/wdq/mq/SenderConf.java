package com.wdq.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wudq
 * @Date: 2018/11/1
 */
@Configuration
public class SenderConf {

    @Bean
    public Queue queue() {
        return new Queue("queue");
    }
}