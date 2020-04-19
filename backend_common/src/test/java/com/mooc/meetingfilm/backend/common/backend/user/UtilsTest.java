package com.mooc.meetingfilm.backend.common.backend.user;

import com.mooc.meetingfilm.utils.vo.BaseResponseVO;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void test(){
        BaseResponseVO responseVO=new BaseResponseVO();
        System.out.println(responseVO.run("helloWorld!"));
    }
}
