package com.mooc.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

public interface FilmServiceAPI {
    //获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize) throws CommonServiceException;

    //获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage,int pageSize) throws CommonServiceException;
}
