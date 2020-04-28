package com.mooc.meetingfilm.hall.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class HallsRepVO extends BasePageVO {
    private String cinemaId;

    @Override
    public void checkParam() throws CommonServiceException {
        super.checkParam();
    }
}
