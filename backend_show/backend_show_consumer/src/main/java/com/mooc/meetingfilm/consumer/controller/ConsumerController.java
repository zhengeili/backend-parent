package com.mooc.meetingfilm.consumer.controller;

import com.mooc.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    private ConsumerServiceAPI serviceAPI;
    @RequestMapping(value = "/sayhello")
    public String sayHello(String message){
        return serviceAPI.sayHello(message);
    }
}
