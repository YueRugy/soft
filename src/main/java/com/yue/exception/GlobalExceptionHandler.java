package com.yue.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yue on 2017/9/7
 * 同意异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo jsonErrorHandler(SoftException e, HttpServletRequest request) {
        ErrorInfo info = new ErrorInfo();
        info.setCode(e.getCode());
        info.setMessage(e.getMessage());
        info.setUrl(request.getRequestURL().toString());
        return info;
    }

    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo jsonErrorHandler(Exception e, HttpServletRequest request) {
        ErrorInfo info = new ErrorInfo();
        info.setCode(NumberConstant.ERROR_NUMBER);
        info.setMessage(e.getMessage());
        info.setUrl(request.getRequestURL().toString());
        return info;
    }*/
}
