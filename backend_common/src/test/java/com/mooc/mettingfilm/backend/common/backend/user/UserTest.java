package com.mooc.mettingfilm.backend.common.backend.user;

import com.mooc.mettingfilm.backend.common.BackendCommonApplicationTests;
import com.mooc.mettingfilm.backend.common.dao.entity.MoocBackendUserT;
import com.mooc.mettingfilm.backend.common.dao.mapper.MoocBackendUserTMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendCommonApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;
    @Test
    public void add(){
        MoocBackendUserT user =new MoocBackendUserT();
        user.setUserName("admin");
        user.setUserPwd("admin");
        user.setUserPhone("13811111111");
        backendUser.insert(user);
    }

    @Test
    public void select(){
//        MoocBackendUserT moocBackendUserT = backendUserTMapper.selectById(2);
//        System.out.println("moocBackendUserT="+moocBackendUserT);
        List<MoocBackendUserT> user = backendUser.selectList(null);
        user.stream().forEach(
                System.out::println
        );

    }

    @Test
    public void update(){
        MoocBackendUserT user=new MoocBackendUserT();
        user.setUuid(3);
        user.setUserName("haha");
        user.setUserPwd("haha");
        user.setUserPhone("12321234567");

        backendUser.updateById(user);
    }

    @Test
    public void del(){
        backendUser.deleteById(3);
    }

}
