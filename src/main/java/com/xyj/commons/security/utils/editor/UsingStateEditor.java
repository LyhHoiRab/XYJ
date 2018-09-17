package com.xyj.commons.security.utils.editor;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.entity.consts.UsingState;

import java.beans.PropertyEditorSupport;

public class UsingStateEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        if(!StringUtils.isBlank(text)){
            int id = Integer.parseInt(text);
            setValue(UsingState.getById(id));
        }
    }
}
