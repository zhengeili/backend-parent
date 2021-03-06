package com.mooc.meetingfilm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerServiceImpl implements ConsumerServiceAPI {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Override
    public String sayHello(String message) {
        //准备工作
//        String hostname="localhost";
//        int port=7101;//这是provider的端口
//        String uri="/provider/sayhello?message="+message;
        // GET Register
        ServiceInstance choose = eurekaClient.choose("hello-service-provider");
        String hostname=choose.getHost();
        int port=choose.getPort();
        String uri="/provider/sayhello?message="+message;
        //String url="http://"+hostname+":"+port+uri;
        String url="http://hello-service-provider:"+port+uri;

        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
