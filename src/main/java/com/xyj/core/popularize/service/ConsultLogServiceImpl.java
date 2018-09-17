package com.xyj.core.popularize.service;

import com.xyj.commons.security.utils.ExcelUtils;
import com.xyj.core.popularize.consts.ConsultStatus;
import com.xyj.core.popularize.consts.ConsultType;
import com.xyj.core.popularize.dao.ConsultLogDao;
import com.xyj.core.popularize.entity.ConsultLog;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ConsultLogServiceImpl implements ConsultLogService{

    @Autowired
    private ConsultLogDao consultLogDao;

    @Override
    @Transactional
    public void save(ConsultLog log){
        Assert.notNull(log, "咨询信息不能为空");
        Assert.hasText(log.getCustomer(), "客户名不能为空");
        Assert.hasText(log.getUrl(), "来源URL不能为空");
        Assert.notNull(log.getType(), "咨询类型不能为空");

        consultLogDao.saveOrUpdate(log);
    }

    @Override
    @Transactional
    public void update(ConsultLog log){
        Assert.notNull(log, "咨询信息不能为空");
        Assert.hasText(log.getId(), "咨询信息ID不能为空");

        consultLogDao.saveOrUpdate(log);
    }

    @Override
    public XSSFWorkbook export(String url, ConsultStatus status, ConsultType type, Date minCreateTime, Date maxCreateTime){
        List<ConsultLog> list = consultLogDao.find(url, status, type, minCreateTime, maxCreateTime);

        //Excel内容
        Map<String, String> titles = new LinkedHashMap<String, String>();
        titles.put("咨询时间", "createTime");
        titles.put("客户名称", "customer");
        titles.put("联系电话", "phone");
        titles.put("联系微信", "wechat");
        titles.put("咨询内容", "content");
        titles.put("来源", "url");
        titles.put("咨询类型", "type");
        titles.put("咨询状态", "status");

        return ExcelUtils.write(titles, list, null);
    }
}
