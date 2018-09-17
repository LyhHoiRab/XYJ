package com.xyj.core.popularize.consts.handler;

import com.xyj.core.popularize.consts.ConsultType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultTypeHandler extends BaseTypeHandler<ConsultType> implements TypeHandler<ConsultType>{

    public ConsultTypeHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, ConsultType parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public ConsultType getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return ConsultType.getById(rs.getInt(columnName));
    }

    public ConsultType getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return ConsultType.getById(rs.getInt(columnIndex));
    }

    public ConsultType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return ConsultType.getById(cs.getInt(columnIndex));
    }
}
