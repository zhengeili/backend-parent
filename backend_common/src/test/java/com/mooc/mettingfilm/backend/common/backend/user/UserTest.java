package com.mooc.mettingfilm.backend.common.backend.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        for (int i = 0; i <5 ; i++) {
            MoocBackendUserT user =new MoocBackendUserT();
            user.setUserName("admin"+i);
            user.setUserPwd("admin"+i);
            user.setUserPhone("1381111111"+i);
            backendUser.insert(user);
        }

    }

    @Test
    public void select(){
//        MoocBackendUserT moocBackendUserT = backendUserTMapper.selectById(2);
//        System.out.println("moocBackendUserT="+moocBackendUserT);
        //查询列表带条件
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("user_name","admin");
        List<MoocBackendUserT> user = backendUser.selectList(queryWrapper);
        user.stream().forEach(
                System.out::println
        );

    }
    @Test
    public void selectByPage(){
        Page<MoocBackendUserT> page=new Page<>(1,3);

        //条件
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.like("user_name","admin");
        IPage<MoocBackendUserT> iPage = backendUser.selectPage(page, wrapper);
        iPage.getRecords().stream().forEach(
                System.out::println
        );
    }

    @Test
    public void update(){
//        MoocBackendUserT user=new MoocBackendUserT();
//        user.setUuid(3);
//        user.setUserName("haha");
//        user.setUserPwd("haha");
//        user.setUserPhone("12321234567");
//
//        backendUser.updateById(user);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_name","admin4");
        MoocBackendUserT user=new MoocBackendUserT();

        user.setUserName("admin-update-QueryWrapper");
        user.setUserPwd("111111");
        user.setUserPhone("111111111111");
        backendUser.update(user,wrapper);

    }

    @Test
    public void del(){
        backendUser.deleteById(3);
    }

}
