package com.mooc.meetingfilm.consumer.controller;

import com.mooc.meetingfilm.consumer.feign.ProviderApi;
import com.mooc.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI serviceAPI;

    @Resource
    private ProviderApi providerApi;

    @RequestMapping(value = "/sayhello/feign")
    public String sayHelloFeign(String message){
        System.out.println("feign message="+message);
        return providerApi.invokerProviderController(message);
    }

    @RequestMapping(value = "/sayhello")
    public String sayHello(String message){

        return serviceAPI.sayHello(message);
    }
}
