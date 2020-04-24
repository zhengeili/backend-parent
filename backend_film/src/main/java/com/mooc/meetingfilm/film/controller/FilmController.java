package com.mooc.meetingfilm.film.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.mooc.meetingfilm.film.service.FilmServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/films")
public class FilmController {

    @Autowired
    private FilmServiceAPI filmServiceAPI;
    //获取演员列表
    @RequestMapping(value = "/actors",method = RequestMethod.GET)
    public BaseResponseVO decribeActors(BasePageVO basePageVO) throws CommonServiceException {
        //检查传参
        basePageVO.checkParam();
        //调用逻辑层，获取范虎参数。
        IPage<DescribeActorsRespVO> results=filmServiceAPI.describeActors(basePageVO.getNowPage(),basePageVO.getPageSize());

        Map<String,Object> actors=describePageResult(results,"actors");
        return BaseResponseVO.success(actors);
    }
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO decribeFilms(BasePageVO basePageVO) throws CommonServiceException {
        //检查传参
        basePageVO.checkParam();
        //调用逻辑层，获取范虎参数。
        IPage<DescribeFilmsRespVO> results=filmServiceAPI.describeFilms(basePageVO.getNowPage(),basePageVO.getPageSize());

        Map<String,Object> actors=describePageResult(results,"films");
        return BaseResponseVO.success(actors);
    }

    @RequestMapping(value = "/{filmId}",method = RequestMethod.GET)
    public BaseResponseVO decribeFilmById(@PathVariable("filmId") String filmId) throws CommonServiceException {
        DescribeFilmRespVO describeFilmRespVO=filmServiceAPI.describeFilmById(filmId);
        if (describeFilmRespVO==null){
            return BaseResponseVO.success();
        }
        return BaseResponseVO.success(describeFilmRespVO);
    }

    @RequestMapping(value = "/film:add",method = RequestMethod.POST)
    public BaseResponseVO saveFilmInfo(@RequestBody FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {
        filmServiceAPI.saveFilm(filmSavedReqVO);
        return BaseResponseVO.success();
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
