package com.mooc.meetingfilm.backend.common.dao.mapper;

import com.mooc.meetingfilm.backend.common.dao.entity.MoocBackendUserT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author wjw
 * @since 2020-04-17
 */
public interface MoocBackendUserTMapper extends BaseMapper<MoocBackendUserT> {

    MoocBackendUserT describeUserByUserName(@Param("username") String username);
}
