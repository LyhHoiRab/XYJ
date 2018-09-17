package com.xyj.core.popularize.dao.mapper;

import com.xyj.core.popularize.entity.ConsultLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Mapper
@Repository
public interface ConsultLogMapper{

    void save(ConsultLog log);

    void update(ConsultLog log);

    ConsultLog get(@Param("params") Criteria criteria);

    List<ConsultLog> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
