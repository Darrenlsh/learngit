package com.darren.cn.eureka_first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaFirstApplication.class, args);
    }

}
