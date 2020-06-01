package com.mooc.meetingfilm.hall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.hall.apis.FilmFeignApi;
import com.mooc.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRepVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class HallServiceImpl implements HallServiceAPI{
    @Resource
    private MoocFieldTMapper fieldTMapper;
    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Resource
    private FilmFeignApi filmFeignApi;

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

//    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws  CommonServiceException{
//
//        //get REGISTER
//        ServiceInstance choose = eurekaClient.choose("film-service");
//
//        //组织调用参数
//        String hostName = choose.getHost();
//        int port=choose.getPort();
//        String uri="/films/"+filmId;
//        String url="http://"+hostName+":"+port+uri;
//        //通过restTemplate调用影片服务
//        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);
//        //解析返回值
//        JSONObject dataJson = baseResponseVO.getJSONObject("data");
//
//        //组织参数
//        MoocHallFilmInfoT hallFilmInfo=new MoocHallFilmInfoT();
//        hallFilmInfo.setFilmId(dataJson.getIntValue("filmId"));
//        hallFilmInfo.setFilmName(dataJson.getString("filmName"));
//        hallFilmInfo.setFilmLength(dataJson.getString("filmLength"));
//        hallFilmInfo.setFilmCats(dataJson.getString("filmCats"));
//        //hallFilmInfo.setFilmLanguage("");//默认生成好的，这里不再赋值
//        hallFilmInfo.setActors(dataJson.getString("actors"));
//        hallFilmInfo.setImgAddress(dataJson.getString("imgAddress"));
//
//        return  hallFilmInfo;
//    }

    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws  CommonServiceException{
        BaseResponseVO<DescribeFilmRespVO> baseResponseVO = filmFeignApi.decribeFilmById(filmId);
        DescribeFilmRespVO filmResult = baseResponseVO.getData();
        if(filmResult == null || ToolUtils.strIsNull(filmResult.getFilmId())){
            throw new CommonServiceException(404,"抱歉，未能找到对应的电影信息 filmId:"+filmId);
        }
        MoocHallFilmInfoT hallFilmInfo=new MoocHallFilmInfoT();

        hallFilmInfo.setFilmId(ToolUtils.str2Int(filmResult.getFilmId()));
        hallFilmInfo.setFilmName(filmResult.getFilmName());
        hallFilmInfo.setFilmLength(filmResult.getFilmLength());
        hallFilmInfo.setFilmCats(filmResult.getFilmCats());
        hallFilmInfo.setActors(filmResult.getActors());
        hallFilmInfo.setImgAddress(filmResult.getImgAddress());

        return hallFilmInfo;
    }
}
