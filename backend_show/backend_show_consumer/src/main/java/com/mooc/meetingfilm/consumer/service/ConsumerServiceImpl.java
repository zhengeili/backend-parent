package com.mooc.meetingfilm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerServiceImpl implements ConsumerServiceAPI {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String sayHello(String message) {

        return null;
    }
}
