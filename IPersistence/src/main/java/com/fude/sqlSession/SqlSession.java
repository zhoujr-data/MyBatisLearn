package com.fude.sqlSession;

import java.util.List;

/**
 * @author zhoujr
 * created at 2022/5/26 16:51
 *
 **/
public interface SqlSession {

    // 查询所有
    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    // 查询一个
    <T> T selectOne(String statementId, Object... params) throws Exception;

    // 为
    <T> T getMapper(Class<?> mapperClass);

}
