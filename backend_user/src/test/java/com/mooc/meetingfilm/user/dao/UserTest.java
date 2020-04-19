package com.mooc.meetingfilm.user.dao;

import com.mooc.meetingfilm.user.BackendUserApplicationTests;
import com.mooc.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.mooc.meetingfilm.utils.util.MD5Util;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendUserApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;
    @Test
    public void add(){
        MoocBackendUserT user=new MoocBackendUserT();
        user.setUserName("admin");
        user.setUserPwd(MD5Util.encrypt("admin123"));
        user.setUserPhone("13581111111");

        backendUser.insert(user);
    }

    @Test
    public void select(){
        List<MoocBackendUserT> user=backendUser.selectList(null);
        user.stream().forEach(
                System.out::println
        );
    }

}
