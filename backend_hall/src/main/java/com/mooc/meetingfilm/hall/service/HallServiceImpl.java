package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;

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
     * @param hallSavedReqVO
     * @throws CommonServiceException
     */
    @Override
    public void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException {

    }
}
