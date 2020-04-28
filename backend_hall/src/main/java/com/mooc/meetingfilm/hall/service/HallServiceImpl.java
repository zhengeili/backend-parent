package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

public class HallServiceImpl implements HallServiceAPI{
    @Override
    public IPage<HallsRespVO> describeHalls(HallsRepVO hallsReqVO) throws CommonServiceException {
        return null;
    }

    @Override
    public void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException {

    }
}
