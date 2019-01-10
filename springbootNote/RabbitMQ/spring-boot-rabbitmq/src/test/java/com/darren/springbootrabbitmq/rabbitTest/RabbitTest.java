package com.darren.springbootrabbitmq.rabbitTest;

import com.darren.springbootrabbitmq.many.DarrenSender1;
import com.darren.springbootrabbitmq.many.DarrenSender2;
import com.darren.springbootrabbitmq.rabbit.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: luosihao
 * @date: 2019/1/9 16:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {
    @Autowired
    private Sender sender;
    @Autowired
    private DarrenSender1 darrenSender1;
    @Autowired
    private DarrenSender2 darrenSender2;

    @Test
    public void hello() throws Exception {
        sender.send();
    }

    /**
     * 一对多和多对多
     */
    @Test
    public void ontToMany(){
        for(int i=1; i<=100; i++){
            darrenSender1.darrenSender1(i);
            darrenSender2.darrenSender2(i);
        }
    }
}
