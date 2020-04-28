package com.mooc.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
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

        return null;
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
