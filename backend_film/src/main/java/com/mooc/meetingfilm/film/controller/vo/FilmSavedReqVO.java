package com.mooc.meetingfilm.film.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseRequestVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class FilmSavedReqVO extends BaseRequestVO {

    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String mainImgAddress;
    private String filmScore;//电影评分
    private String filmScorers;//评分人数
    private String preSaleNum;
    private String boxOffice;
    private String filmTypeId;
    private String filmSourceId;
    private String filmCatIds;
    private String areaId;
    private String dateId;
    private String filmTime;
    private String directorId;
    private String actIds;//演员列表
    private String roleNames;//角色名称
    private String filmLength;
    private String biography;
    private String filmImgs;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
