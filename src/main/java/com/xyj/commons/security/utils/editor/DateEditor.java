package com.xyj.commons.security.utils.editor;

import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.utils.DateUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.regex.Pattern;

public class DateEditor extends PropertyEditorSupport{

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        Date date = null;

        if(StringUtils.isNotBlank(text)){
            if(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(text).matches()){
                date = DateUtils.parse(text, "yyyy-MM-dd HH:mm:ss", false);

            }else if(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(text).matches()){
                date = DateUtils.parse(text, "yyyy-MM-dd", false);

            }else if(Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}").matcher(text).matches()){
                date = DateUtils.parse(text, "yyyy/MM/dd", false);

            }
        }
        setValue(date);
    }
}

