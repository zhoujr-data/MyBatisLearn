package com.fude.sqlSession;

import com.fude.pojo.Configuration;
import com.fude.pojo.MapperStatement;

import java.util.List;

/**
 * @author zhoujr
 * created at 2022/5/26 17:05
 * //TODO
 **/
public interface Executor {

    <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception;

}
