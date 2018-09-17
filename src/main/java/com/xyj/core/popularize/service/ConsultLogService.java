package com.xyj.core.popularize.service;

import com.xyj.core.popularize.consts.ConsultStatus;
import com.xyj.core.popularize.consts.ConsultType;
import com.xyj.core.popularize.entity.ConsultLog;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

public interface ConsultLogService{

    void save(ConsultLog log);

    void update(ConsultLog log);

    XSSFWorkbook export(String url, ConsultStatus status, ConsultType type, Date minCreateTime, Date maxCreateTime);
}
