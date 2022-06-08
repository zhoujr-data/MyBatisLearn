package com.fude.sqlSession.impl;

import com.fude.config.BoundSql;
import com.fude.pojo.Configuration;
import com.fude.pojo.MapperStatement;
import com.fude.sqlSession.Executor;
import com.fude.utils.GenericTokenParser;
import com.fude.utils.ParameterMapping;
import com.fude.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujr
 * created at 2022/5/26 17:08
 * 底层执行逻辑，注册驱动获取连接，解析sql,反射入参，执行sql,内省出参
 **/
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception {
        // 注册驱动
        Connection connection = configuration.getDataSource().getConnection();
        // 获取sql语句： select * from user where id = #{id} and name = #{name}
        String sql = mapperStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        // 获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //设置参数
        String parameterType = mapperStatement.getParameterType();
        Class<?> parameterClass = getClassType(parameterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            // 反射
            Field declaredField = parameterClass.getDeclaredField(content);
            // 暴力访问
            declaredField.setAccessible(Boolean.TRUE);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1, o);
        }
        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 封装返回
        String resultType = mapperStatement.getResultType();
        Class<?> resultClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        while (resultSet.next()) {
            Object o = resultClass.getDeclaredConstructor().newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object object = resultSet.getObject(columnName);
                // 使用反射对应实体关系
                // 内省包中的类
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, object);
            }
            objects.add(o);
        }

        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            return Class.forName(parameterType);
        }
        return null;
    }

    /**
     * 完成对#{}的解析工作
     *
     * @param sql sql
     * @return 绑定的对象
     **/
    private BoundSql getBoundSql(String sql) {
        // 标记处理类
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析出来的Sql
        String parseSql = genericTokenParser.parse(sql);
        // #{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parseSql, parameterMappings);
    }
}
