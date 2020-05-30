package com.mooc.meetingfilm.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "providerTest",url = "http://localhost:7101")
public interface ProviderApi {
    @RequestMapping(value="/provider/sayhello",method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message") String message);
}
