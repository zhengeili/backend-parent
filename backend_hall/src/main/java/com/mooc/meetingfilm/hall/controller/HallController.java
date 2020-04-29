package com.mooc.meetingfilm.hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.hall.service.HallServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/halls")
public class HallController {

    @Resource
    private HallServiceAPI hallServiceAPI;


    @RequestMapping(value = "/hall:add",method = RequestMethod.POST)
    public BaseResponseVO saveHall(@RequestBody HallSavedReqVO reqVO) throws CommonServiceException {

        reqVO.checkParam();

        hallServiceAPI.saveHall(reqVO);

        return BaseResponseVO.success();
    }

    /**
     * 获取播放厅列表
     * @param reqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO decribeHalls(HallsRepVO reqVO) throws CommonServiceException {
        IPage<HallsRespVO> page = hallServiceAPI.describeHalls(reqVO);
        Map<String, Object> halls = describePageResult(page, "halls");
        return BaseResponseVO.success(halls);
    }
    //获取分页对象的公共接口
    private Map<String,Object> describePageResult(IPage page, String title){
        Map<String,Object> result= Maps.newHashMap();
        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());

        result.put(title,page.getRecords());
        return  result;
    }
}
