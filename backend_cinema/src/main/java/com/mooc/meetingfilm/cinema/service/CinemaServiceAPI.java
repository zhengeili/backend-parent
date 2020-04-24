package com.mooc.meetingfilm.cinema.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemaRespVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

public interface CinemaServiceAPI {
    void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException;

    IPage<DescribeCinemaRespVO> decribeCinemas(int nowPage,int pageSize) throws CommonServiceException;
}
