package com.darren.springbootrabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author: luosihao
 * @date: 2019/1/9 16:28
 */
@Configuration
public class RabbitConfig {
    /**
     * 1，定义bean
     * @Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。添加的bean的id为方法名
     * 下面是@Configuration里的一个例子
     * @Configuration
     * public class AppConfig {
     *
     *     @Bean
     *     public TransferService transferService() {
     *         return new TransferServiceImpl();
     *     }
     *
     * }
     * 这就相当于xml文件里面的配置
     * <beans>
     *     <bean id="transferService" class="com.acme.TransferServiceImpl"/>
     * </beans>
     *
     * @return
     */

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

    @Bean
    public Queue darrenQueue(){
        return new Queue("darren");
    }
}
