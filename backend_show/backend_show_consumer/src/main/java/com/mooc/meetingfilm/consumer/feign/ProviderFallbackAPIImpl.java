package com.mooc.meetingfilm.consumer.feign;

import org.springframework.stereotype.Service;

@Service
public class ProviderFallbackAPIImpl implements ProviderApi {
    @Override
    public String invokerProviderController(String message) {
        return "invokerProviderController fallback message="+message;
    }
}
