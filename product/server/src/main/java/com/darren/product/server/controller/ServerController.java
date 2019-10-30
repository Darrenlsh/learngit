package com.darren.product.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luosihao
 * @date 2019/10/16 14:23
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String testMsg(){
        return "this is testMsg";
    }
}
