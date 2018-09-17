package com.xyj.commons.security.config.handler;

import com.xyj.commons.security.utils.editor.DateEditor;
import com.xyj.commons.security.utils.editor.SexEditor;
import com.xyj.commons.security.utils.editor.UsingStateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.Date;

@ControllerAdvice
public class EditorHandler{

    @InitBinder
    public void initDateBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @InitBinder
    public void initSexBinder(WebDataBinder binder){
        binder.registerCustomEditor(Sex.class, new SexEditor());
    }

    @InitBinder
    public void initUsingState(WebDataBinder binder){
        binder.registerCustomEditor(UsingState.class, new UsingStateEditor());
    }
}
