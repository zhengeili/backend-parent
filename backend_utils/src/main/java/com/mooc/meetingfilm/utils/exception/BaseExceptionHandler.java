package com.mooc.meetingfilm.utils.exception;

import com.mooc.meetingfilm.utils.vo.BaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共异常处理类
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(CommonServiceException.class)
    public BaseResponseVO serviceExceptionHandelr(HttpServletRequest request,CommonServiceException e){

        log.error("CommonSerException, Code{}, message",e.getCode(),e.getMessage());
        return BaseResponseVO.serviceException(e);
    }
}
