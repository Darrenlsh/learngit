package com.darren.springbootrabbitmq.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: luosihao
 * @date: 2019/1/9 16:48
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        // rabbitTemplate是springboot 提供的默认实现
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
