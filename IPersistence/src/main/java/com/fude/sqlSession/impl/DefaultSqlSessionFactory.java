package com.fude.sqlSession.impl;

import com.fude.pojo.Configuration;
import com.fude.sqlSession.SqlSession;
import com.fude.sqlSession.SqlSessionFactory;

/**
 * @author zhoujr
 * created at 2022/5/26 16:47
 * 生产SqlSession对象
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
