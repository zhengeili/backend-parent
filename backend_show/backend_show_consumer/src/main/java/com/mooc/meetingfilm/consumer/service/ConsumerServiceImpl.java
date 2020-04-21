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
        //准备工作
        String hostname="localhost";
        int port=7101;//这是provider的端口
        String uri="/provider/sayhello?message="+message;

        String url="http://"+hostname+":"+port+uri;
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
