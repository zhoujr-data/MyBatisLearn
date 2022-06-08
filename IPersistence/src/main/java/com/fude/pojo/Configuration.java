package com.fude.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujr
 * created at 2022/5/23 20:24
 * 配置文件
 **/
public class Configuration {

    private DataSource dataSource;

    /**
     * key: statementId value：封装好的statementMapper对象
     **/
    private Map<String, MapperStatement> mapperStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MapperStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }
}
