package com.yuxiao.wechat.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理方法
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private String exceptionHandler(HttpServletRequest request, Exception exception){
        return exception.getMessage();
    }

}
