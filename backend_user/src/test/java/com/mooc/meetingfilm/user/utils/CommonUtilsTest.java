package com.mooc.meetingfilm.user.utils;

import com.mooc.meetingfilm.utils.vo.BaseResponseVO;
import org.junit.jupiter.api.Test;

public class CommonUtilsTest {
    @Test
    public void test(){
        BaseResponseVO responseVO=new BaseResponseVO();
        System.out.println(responseVO.run("user"));
    }
}
