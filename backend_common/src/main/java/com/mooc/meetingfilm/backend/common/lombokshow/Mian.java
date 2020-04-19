package com.mooc.meetingfilm.backend.common.lombokshow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mian {
    public static void main(String[] args){
        UserModel userModel=new UserModel();
        userModel.setId("1111");

        UserModel user=UserModel.builder().Id("1")
                .username("2222")
                .userpassword("333").build();

        log.info("日志info");
        log.error("哈哈哈哈");
    }
}
