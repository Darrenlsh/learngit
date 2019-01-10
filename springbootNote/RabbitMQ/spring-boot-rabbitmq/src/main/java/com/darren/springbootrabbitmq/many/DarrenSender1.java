package com.darren.springbootrabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: luosihao
 * @date: 2019/1/10 16:57
 */
@Component
public class DarrenSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void darrenSender1(int i){
        String context = "spring boot darrenSender1 queue"+"******"+i;
        System.out.println("Sender1:"+context);
        this.rabbitTemplate.convertAndSend("darren",context);
    }

}
