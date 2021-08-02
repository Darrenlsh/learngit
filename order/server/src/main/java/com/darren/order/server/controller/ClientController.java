package com.darren.order.server.controller;

import com.darrren.product.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author luosihao
 * @date 2019/10/16 14:39
 */
@RestController
@RequestMapping("/clientController")
@Slf4j
public class ClientController {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

//    @GetMapping("/testProductServer")
//    public String testProductServer(){
//        String response = productClient.productMsg();
//        log.info("response = {},status = {}",response,response);
//        return response;
//    }

    @GetMapping("/testRestTemplate")
    public String testRestTemplate(){
        // 方式一，直接用restTemplate,url写死
//        RestTemplate restTemplate = new RestTemplate();
//        String response =  restTemplate.getForObject("http://localhost:8081/msg",String.class);
//        System.out.println(response);
//        return response;
        // 方式二，直接用restTemplate,利用loadBalancerClient通过应用名获取url
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/msg";
//        String response = restTemplate.getForObject(url,String.class);
//        return response;
        // 方式三，利用@LoadBalanced（在restTemplateConfig中配置）,可在restTemplate里使用应用名字
        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);
        return response;
    }
}
