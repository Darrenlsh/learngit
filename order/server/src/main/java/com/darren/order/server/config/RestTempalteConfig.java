package com.darren.order.server.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author luosihao
 * @date 2019/10/18 11:29
 */
@Configuration
public class RestTempalteConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTempalte(){
        return new RestTemplate();
    }
}
