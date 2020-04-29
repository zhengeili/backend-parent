package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HallServiceImpl implements HallServiceAPI{
    @Resource
    private MoocFieldTMapper fieldTMapper;
    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    /**
     * 查询影厅列表
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<HallsRespVO> describeHalls(HallsRepVO hallsReqVO) throws CommonServiceException {
        Page<HallsRepVO> page=new Page<>(hallsReqVO.getNowPage(),hallsReqVO.getPageSize());

        QueryWrapper queryWrapper=new QueryWrapper();
        if(ToolUtils.strIsNotNul(hallsReqVO.getCinemaId())){
            queryWrapper.eq("cinema_id",hallsReqVO.getCinemaId());
        }
        IPage<HallsRespVO> result = fieldTMapper.decribeHalls(page, queryWrapper);

        return result;
    }

    /**
     * 保存影厅信息
     * @param reqVO
     * @throws CommonServiceException
     */
    @Override
    public void saveHall(HallSavedReqVO reqVO) throws CommonServiceException {
        //播放厅列表数据
        MoocFieldT field=new MoocFieldT();

        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));

        fieldTMapper.insert(field);

        //播放厅对应的影片数据，影片荣誉数据，缓存有一份
        MoocHallFilmInfoT hallFilmInfo=describeFilmInfo(reqVO.getFilmId());

        hallFilmInfoTMapper.insert(hallFilmInfo);
    }
    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws  CommonServiceException{
        MoocHallFilmInfoT hallFilmInfo=new MoocHallFilmInfoT();
        hallFilmInfo.setFilmId(0);
        hallFilmInfo.setFilmName("");
        hallFilmInfo.setFilmLength("");
        hallFilmInfo.setFilmCats("");
        //hallFilmInfo.setFilmLanguage("");//默认生成好的，这里不再赋值
        hallFilmInfo.setActors("");
        hallFilmInfo.setImgAddress("");

        return  hallFilmInfo;
    }
}
