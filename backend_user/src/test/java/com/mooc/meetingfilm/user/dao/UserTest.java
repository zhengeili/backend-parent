package com.mooc.meetingfilm.user.dao;

import com.mooc.meetingfilm.user.BackendUserApplicationTests;
import com.mooc.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendUserApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;
    @Test
    public void add(){

    }

    @Test
    public void select(){
        List<MoocBackendUserT> user=backendUser.selectList(null);
        user.stream().forEach(
                System.out::println
        );
    }

}
