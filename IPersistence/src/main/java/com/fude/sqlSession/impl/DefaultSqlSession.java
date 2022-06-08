package com.fude.sqlSession.impl;

import com.fude.pojo.Configuration;
import com.fude.pojo.MapperStatement;
import com.fude.sqlSession.Executor;
import com.fude.sqlSession.SqlSession;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author zhoujr
 * created at 2022/5/26 16:52
 * 执行查询
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //
        Executor simpleExecutor = new SimpleExecutor();
        MapperStatement mapperStatement = configuration.getMapperStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mapperStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = this.selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理生成代理对象

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // proxy 当前代理对象的应用 method 当前被调用方法的引用 args 传递的参数
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;
                // 获取返回值类型
                Type type = method.getGenericReturnType();
                // 判断是否进行了 泛型类型参数化
                if (type instanceof ParameterizedType) {
                    return selectList(statementId, args);
                }
                return selectOne(statementId, args);
            }
        });

        return (T) proxyInstance;
    }
}
