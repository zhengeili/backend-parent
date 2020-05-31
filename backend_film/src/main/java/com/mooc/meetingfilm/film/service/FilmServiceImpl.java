package com.mooc.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
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
import com.mooc.meetingfilm.utils.util.ToolUtils;
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
    public void saveFilm(FilmSavedReqVO reqVO) throws CommonServiceException {
        try{
            // 保存电影主表
            MoocFilmT film=new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());//	电影评分
            //film.setFilmScorers();//电影评分人数
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));//	电影预售票房
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));


            filmTMapper.insert(film);
            // 保存电影子表
            MoocFilmInfoT filmInfo=new MoocFilmInfoT();
            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());

            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());


            filmInfoTMapper.insert(filmInfo);

            String[] actorId=reqVO.getActIds().split("#");
            String[] roleNames=reqVO.getRoleNames().split("#");
            if(actorId.length!=roleNames.length){
                throw new CommonServiceException(500,"演员和角色名数量不匹配");
            }

            for (int i = 0; i <actorId.length ; i++) {
                // 保存演员映射表
                MoocFilmActorT filmActor=new MoocFilmActorT();
                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);
                moocFilmActorTMapper.insert(filmActor);
            }

        }catch (Exception e){
            throw new CommonServiceException(500,e.getMessage());//把所有的异常都抛出
        }
    }
}
