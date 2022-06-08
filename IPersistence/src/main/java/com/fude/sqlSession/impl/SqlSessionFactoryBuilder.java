package com.fude.sqlSession.impl;

import com.fude.config.XMLConfigBuilder;
import com.fude.pojo.Configuration;
import com.fude.sqlSession.SqlSessionFactory;

import java.io.InputStream;

/**
 * @author zhoujr
 * created at 2022/5/23 20:31
 * 生产SqlSessionFactory对象
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory builder(InputStream in) throws Exception {
        // 1.使用dom4J解析配置文件
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);
        // 2.创建sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
