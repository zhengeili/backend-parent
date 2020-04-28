package com.mooc.meetingfilm.hall.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author wjw
 * @since 2020-04-24
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {

    IPage<HallsRespVO> decribeHalls(Page<HallsRespVO> page,@Param("cinemaId") String cinemaId);
}
