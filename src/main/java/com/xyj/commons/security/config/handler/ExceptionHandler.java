package com.xyj.commons.security.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wah.doraemon.security.consts.ResponseCode;
import org.wah.doraemon.security.response.Responsed;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler{

    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 其他异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Responsed exception(Exception e){
        logger.error(e.getMessage(), e);

        return new Responsed(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR, false);
    }
}
