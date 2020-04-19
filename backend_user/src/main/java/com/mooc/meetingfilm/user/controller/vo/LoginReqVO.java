package com.mooc.meetingfilm.user.controller.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import com.mooc.meetingfilm.utils.vo.BaseRequestVO;
import lombok.Data;

@Data
public class LoginReqVO extends BaseRequestVO {
    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        // TODO 验证数据合法性
        if(ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)){
            throw new CommonServiceException(404,"用户名或密码不能为空");
        }
    }
}
