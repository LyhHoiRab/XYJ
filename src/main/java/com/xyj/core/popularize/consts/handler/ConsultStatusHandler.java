package com.xyj.core.popularize.consts.handler;

import com.xyj.core.popularize.consts.ConsultStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultStatusHandler extends BaseTypeHandler<ConsultStatus> implements TypeHandler<ConsultStatus>{

    public ConsultStatusHandler(){
    }

    public void setNonNullParameter(PreparedStatement ps, int i, ConsultStatus parameter, JdbcType jdbcType) throws SQLException{
        ps.setInt(i, parameter.getId());
    }

    public ConsultStatus getNullableResult(ResultSet rs, String columnName) throws SQLException{
        return ConsultStatus.getById(rs.getInt(columnName));
    }

    public ConsultStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        return ConsultStatus.getById(rs.getInt(columnIndex));
    }

    public ConsultStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        return ConsultStatus.getById(cs.getInt(columnIndex));
    }
}
