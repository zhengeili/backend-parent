package com.mooc.meetingfilm.consumer.feign;

import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "providerTest",path = "/provider",url = "http://localhost:7101",primary = true)
public interface ProviderApi {
    @RequestMapping(value = "/sayhello", method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message") String message);

    @RequestMapping(value = "/{providerId}/sayhello", method = RequestMethod.POST)
    String providerPost(
            @RequestHeader("author") String author,
            @PathVariable("providerId") String providerId,
            @RequestBody UserModel userModel);
}
