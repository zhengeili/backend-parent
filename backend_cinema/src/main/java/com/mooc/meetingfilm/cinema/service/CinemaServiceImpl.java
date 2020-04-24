package com.mooc.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemaRespVO;
import com.mooc.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mooc.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CinemaServiceImpl implements CinemaServiceAPI {
    @Resource
    private MoocCinemaTMapper cinemaTMapper;
    @Override
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {
        MoocCinemaT cinema=new MoocCinemaT();

        // TODO 添加具体参数
        cinema.setCinemaName(reqVO.getCinemaName());
        cinema.setCinemaPhone(reqVO.getCinemaTele());//影院电话 这里的单词应该是拼错，教程中也是拼错的
        cinema.setBrandId(ToolUtils.str2Int(reqVO.getBrandId()));//	所属品牌编号
        cinema.setAreaId(ToolUtils.str2Int(reqVO.getAreaId()));//	所属地域编号
        cinema.setHallIds(reqVO.getHallTypeIds());//所属影厅类型编号
        cinema.setImgAddress(reqVO.getCinemaImgAddress());//影院主图服务器相对路径
        cinema.setCinemaAddress(reqVO.getCinemaAddress());
        cinema.setMinimumPrice(ToolUtils.str2Int(reqVO.getCinemaPrice()));//影院最低票价

        // TODO 记得把实体对象进行保存
        cinemaTMapper.insert(cinema);
    }

    @Override
    public IPage<DescribeCinemaRespVO> decribeCinemas(int nowPage, int pageSize) throws CommonServiceException {
        // 查询实体对象，然后与表现层对象进行交互
        Page<MoocCinemaT> page=new Page<>(nowPage,pageSize);
        IPage<MoocCinemaT> moocCinemaTIPage=cinemaTMapper.selectPage(page,null);
        // moocCinemaTIPage对象内的分页参数与IPage<DescribeCinemaRespVO> 一模一样
        moocCinemaTIPage.getRecords();//set到IPage<DescribeCinemaResqVO> records方法中。


        return null;
    }
}
