package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

public interface HallServiceAPI {
    //获取全部播放厅接口
    IPage<HallsRespVO> describeHalls(HallsRepVO hallsReqVO) throws CommonServiceException;
}
