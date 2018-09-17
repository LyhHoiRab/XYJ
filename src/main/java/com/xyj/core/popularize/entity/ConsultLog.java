package com.xyj.core.popularize.entity;

import com.xyj.core.popularize.consts.ConsultStatus;
import com.xyj.core.popularize.consts.ConsultType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class ConsultLog extends Entity implements Createable, Updateable{

    //客户名称
    private String        customer;
    //联系电话
    private String        phone;
    //联系微信
    private String        wechat;
    //咨询内容
    private String        content;
    //来源
    private String        url;
    //咨询类型(0:售前咨询, 1:售后咨询)
    private ConsultType   type;
    private ConsultStatus status;
    private Date          createTime;
    private Date          updateTime;
}
