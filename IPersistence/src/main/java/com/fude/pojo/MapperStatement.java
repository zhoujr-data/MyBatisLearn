package com.fude.pojo;

/**
 * @author zhoujr
 * created at 2022/5/23 17:47
 * mapper中的select/update/delete/insert
 **/
public class MapperStatement {

    // id
    private String id;
    // 返回值类型
    private String resultType;
    // 参数类型
    private String parameterType;
    // SQL语句
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
