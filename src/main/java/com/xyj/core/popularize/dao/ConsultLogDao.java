package com.xyj.core.popularize.dao;

import com.xyj.core.popularize.consts.ConsultStatus;
import com.xyj.core.popularize.consts.ConsultType;
import com.xyj.core.popularize.dao.mapper.ConsultLogMapper;
import com.xyj.core.popularize.entity.ConsultLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.DateUtils;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class ConsultLogDao{

    private Logger logger = LoggerFactory.getLogger(ConsultLogDao.class);

    @Autowired
    private ConsultLogMapper mapper;

    public void saveOrUpdate(ConsultLog log){
        try{
            Assert.notNull(log, "咨询信息不能为空");

            if(StringUtils.isBlank(log.getId())){
                Assert.hasText(log.getCustomer(), "客户名不能为空");
                Assert.hasText(log.getUrl(), "来源URL不能为空");
                Assert.notNull(log.getType(), "咨询类型不能为空");

                log.setId(IDGenerator.uuid32());
                log.setStatus(ConsultStatus.UNHANDLE);
                log.setCreateTime(new Date());
                mapper.save(log);
            }else{
                log.setUpdateTime(new Date());
                mapper.update(log);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<ConsultLog> find(String url, ConsultStatus status, ConsultType type, Date minCreateTime, Date maxCreateTime){
        try{
            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(url)){
                criteria.and(Restrictions.like("url", url));
            }
            if(status != null){
                criteria.and(Restrictions.eq("status", status.getId()));
            }
            if(type != null){
                criteria.and(Restrictions.eq("type", type.getId()));
            }
            if(minCreateTime != null){
                minCreateTime = DateUtils.firstTimeOfDate(minCreateTime);

                criteria.and(Restrictions.ge("createTime", minCreateTime));
            }
            if(maxCreateTime != null){
                maxCreateTime = DateUtils.lastTimeOfDate(maxCreateTime);

                criteria.and(Restrictions.le("createTime", maxCreateTime));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}
