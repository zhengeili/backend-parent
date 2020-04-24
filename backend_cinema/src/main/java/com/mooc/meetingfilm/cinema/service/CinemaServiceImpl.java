package com.mooc.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemaRespVO;
import com.mooc.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CinemaServiceImpl implements CinemaServiceAPI {
    @Resource
    private MoocCinemaTMapper cinemaTMapper;
    @Override
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {

    }

    @Override
    public IPage<DescribeCinemaRespVO> decribeCinemas(int nowPage, int pageSize) throws CommonServiceException {
        return null;
    }
}
