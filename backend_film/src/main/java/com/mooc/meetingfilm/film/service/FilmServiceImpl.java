package com.mooc.meetingfilm.film.service;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmInfoT;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmT;
import com.mooc.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FilmServiceImpl implements FilmServiceAPI {
    @Resource
    private MoocActorTMapper actorTMapper;

    @Resource
    private MoocFilmTMapper filmTMapper;

    @Resource
    private MoocFilmInfoTMapper filmInfoTMapper;

    @Resource
    private MoocFilmActorTMapper moocFilmActorTMapper;

    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException {

        return actorTMapper.describeActors(new Page<>(nowPage,pageSize));
    }

    /**
     * 影片列表查询
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException {
        return filmTMapper.describeFilms(new Page<>(nowPage,pageSize));
    }

    /**
     * 根据主键获取电影详情
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        return filmTMapper.describeFilmById(filmId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//用到事务，因为这里要做三个表的保存
    public void saveFilm(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {
        try{
            // 保存电影主表
            MoocFilmT film=new MoocFilmT();
            film.setFilmName("");
            film.setFilmType(0);
            film.setImgAddress("");
            film.setFilmScore("");
            film.setFilmPresalenum(0);
            film.setFilmBoxOffice(0);
            film.setFilmSource(0);
            film.setFilmCats("");
            film.setFilmArea(0);
            film.setFilmDate(0);
            film.setFilmTime(LocalDateTime.now());
            film.setFilmStatus(0);


            filmTMapper.insert(film);
            // 保存电影子表
            MoocFilmInfoT filmInfo=new MoocFilmInfoT();
            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName("");
            filmInfo.setFilmScore("");
            filmInfo.setFilmScoreNum(0);
            filmInfo.setFilmLength(0);
            filmInfo.setBiography("");
            filmInfo.setDirectorId(0);
            filmInfo.setFilmImgs("");


            filmInfoTMapper.insert(filmInfo);
            // 保存演员映射表
            MoocFilmActorT filmActor=new MoocFilmActorT();
            filmActor.setFilmId(film.getUuid());
            filmActor.setActorId(0);
            filmActor.setRoleName("");


            moocFilmActorTMapper.insert(filmActor);

        }catch (Exception e){
            throw new CommonServiceException(500,e.getMessage());//把所有的异常都抛出
        }
    }
}
