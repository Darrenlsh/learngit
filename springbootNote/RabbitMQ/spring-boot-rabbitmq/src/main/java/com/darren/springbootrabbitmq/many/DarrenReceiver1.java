package com.darren.springbootrabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: luosihao
 * @date: 2019/1/9 17:19
 */
@Component
@RabbitListener(queues = "darren")
public class DarrenReceiver1 {
    @RabbitHandler
    public void darrenReceiver1(String darren){
        System.out.println("darrenReceiver1:"+darren);
    }
}
