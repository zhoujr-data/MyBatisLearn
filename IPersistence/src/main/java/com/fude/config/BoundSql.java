package com.fude.config;

import com.fude.utils.ParameterMapping;

import java.util.List;

/**
 * @author zhoujr
 * created at 2022/6/2 14:53
 * 解析出来的sql和对应的字段
 **/
public class BoundSql {
    private String sqlText;
    private List<ParameterMapping> parameterMappingList;

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
