package com.darren.springbootrabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: luosihao
 * @date: 2019/1/9 16:57
 */
@Component
public class DarrenSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void darrenSender2(int i){
        String context = "spring boot darrenSender2 queue"+"******"+i;
        System.out.println("Sender2:"+context);
        this.rabbitTemplate.convertAndSend("darren",context);
    }

}
