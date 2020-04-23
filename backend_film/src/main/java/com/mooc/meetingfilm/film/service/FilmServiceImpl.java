package com.mooc.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmServiceAPI {
    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException {
        return null;
    }

    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException {
        return null;
    }

    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        return null;
    }
}
