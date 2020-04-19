package com.mooc.meetingfilm.backend.common.lombokshow;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    public UserModel(){}
    public String Id;
    public String username;
    public String userpassword;
}
