package com.xyj.commons.security.utils;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.wah.doraemon.domain.consts.EnumType;
import org.wah.doraemon.utils.NameUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class ExcelUtils{

    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> XSSFWorkbook write(Map<String, String> map, List<T> contents, String separator){
        if(map == null || map.isEmpty()){
            throw new IllegalArgumentException("Excel表和实体映射关系不能为空");
        }

        //创建excel
        XSSFWorkbook book = new XSSFWorkbook();
        //创建工作簿
        XSSFSheet sheet = book.createSheet();
        //标题
        List<String> titles = new ArrayList<String>();
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            titles.add(iterator.next());
        }

        //创建标题
        XSSFRow titleRow = sheet.createRow(0);
        for(int i = 0; i < titles.size(); i++){
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titles.get(i));
        }

        //填充正文
        for(int i = 1; i <= contents.size(); i++){
            //创建行
            XSSFRow row = sheet.createRow(i);
            //行内容对象
            T entry = contents.get(i - 1);

            for(int j = 0; j < titles.size(); j++){
                String attribute = map.get(titles.get(j));
                Object value = getValue(entry, attribute, separator);

                //赋值
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(getValueAsString(value));
            }
        }

        return book;
    }

    public static String getValueAsString(Object value){
        if(value == null){
            return "";

        }else if(value instanceof Date){
            return DEFAULT_DATE_FORMAT.format(value);

        }else if(value instanceof Number){
            return value.toString();

        }else if(value instanceof Enum && EnumType.class.isAssignableFrom(value.getClass())){
            try{
                Method method = value.getClass().getMethod("getDescription");
                method.setAccessible(true);
                return method.invoke(value, (Object[]) null).toString();

            }catch(NoSuchMethodException e){
                return "";
            }catch(IllegalAccessException e){
                return "";
            }catch(InvocationTargetException e){
                return "";
            }

        }else if(value instanceof Collection){
            Collection collection = (Collection) value;

            if(collection.isEmpty()){
                return "";
            }

            String separator = "; ";
            StringBuffer sb = new StringBuffer();

            for(Object object : collection){
                sb.append(getValueAsString(object)).append(separator);
            }

            int index = sb.lastIndexOf(separator);
            return sb.delete(index, index + separator.length()).toString();
        }else{
            return value.toString();

        }
    }

    public static Object getValue(Object object, String attribute){
        if(object == null){
            return null;
        }

        if(StringUtils.isBlank(attribute)){
            return null;
        }

        try{
            Method method = object.getClass().getMethod("get" + NameUtils.upperCaseToFirst(attribute));
            method.setAccessible(true);

            return method.invoke(object, (Object[]) null);
        }catch(NoSuchMethodException e) {
            return null;
        }catch(IllegalAccessException e){
            return null;
        }catch(InvocationTargetException e){
            return null;
        }
    }

    public static Object getValue(Object object, String attributeReg, String separator){
        if(object == null){
            return null;
        }

        if(StringUtils.isBlank(attributeReg)){
            return null;
        }

        Pattern pattern = Pattern.compile("^[\\w]+\\.");
        Matcher matcher = pattern.matcher(attributeReg);

        if(matcher.find()){
            //property.attribute1.attribute2...
            String attribute = matcher.group();
            Object value = getValue(object, attribute.replace(".", ""));

            attributeReg = attributeReg.replace(attribute, "");
            //递归
            if(StringUtils.isNotBlank(attribute)){
                if(value instanceof Collection){
                    List<Object> list = new ArrayList<Object>();

                    for(Object entry : (Collection) value){
                        //递归
                        if(StringUtils.isNotBlank(attribute)){
                            list.add(getValue(entry, attributeReg, separator));
                        }
                    }

                    return list;
                }else{
                    return getValue(value, attributeReg, separator);
                }
            }

            return value;
        }else{
            //(attribute1)(attribute2)
            matcher = Pattern.compile("\\([\\S+]+\\)*").matcher(attributeReg);

            if(matcher.matches()){
                matcher = Pattern.compile("\\([A-Za-z._]*?(\\([A-Za-z._]+\\))*?\\)").matcher(attributeReg);

                StringBuffer sb = new StringBuffer();
                while(matcher.find()){
                    //(attribute1)
                    String attribute = matcher.group();
                    attribute = attribute.substring(attribute.indexOf("(") + 1, attribute.lastIndexOf(")"));
                    Object value = getValue(object, attribute, separator);

                    sb.append(value).append(separator);
                }

                int index = sb.lastIndexOf(separator);
                return sb.delete(index, index + separator.length()).toString();
            }else{
                //attribute
                return getValue(object, attributeReg);
            }
        }
    }
}
