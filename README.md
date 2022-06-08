# 本项目用于学习MyBatis原理，模仿MyBatis自定义的持久层框架

> 主要分未两个模块，IPersistence是自定义的持久层框架，IPersistence_test是用于测试IPersistence。
>
> 涉及到的设计模式：Builder构建者设计模式、⼯⼚模式、代理模式

## 一、基于原始JDBC开发存在以下问题：
1、数据库连接创建、释放频繁造成系统资源浪费，从⽽影响系统性能。 

2、Sql语句在代码中硬编码，造成代码不易维护，实际应⽤中sql变化的可能较⼤，sql变动需要改变 java代码。

3、使用preparedStatement向占有位符号传参数存在硬编码，因为sql语句的where条件不⼀定，可能多也可能少，修改sql还要修改代码，系统不易维护。 

4、对结果集解析存在硬编码(查询列名)，sql变化导致解析代码变化，系统不易维护，如果能将数据 库记录封装成pojo对象解析比较方便

## 二、问题解决思路
1、使⽤数据库连接池初始化连接资源

2、将sql语句抽取到xml配置⽂件中

3、使⽤反射、内省等底层技术，⾃动将实体与表进⾏属性与字段的⾃动映射

## 三、自定义框架设计
### 使用端（提供核心配置文件）
sqlMapConfig.xml : 存放数据源信息，引⼊mapper.xml
Mapper.xml : sql语句的配置⽂件信息

### 框架端：
1.读取配置⽂件，以流的形式读取，创建javaBean存储

（1）Configuration : 存放数据库基本信息、Map<唯⼀标识，Mapper> 唯⼀标识：namespace + "." + id

（2）MappedStatement：sql语句、statement类型、输⼊参数java类型、输出参数java类型

2.解析配置⽂件（方法：SqlSessionFactoryBuilder().builder()）（**工厂模式**）

​	第⼀：使⽤dom4j解析配置⽂件，将解析出来的内容封装到Configuration和MappedStatement中

​			方法：xmlConfigBuilder.parseConfig(InputStream in)：解析数据库配置文件

​			方法：xmlMapperBuilder.parse(InputStream in)：解析mapper配置文件

​	第⼆：创建SqlSessionFactory的实现类DefaultSqlSession

3.创建SqlSessionFactory：

​	⽅法：openSession() : 获取sqlSession接⼝的实现类实例对象

4.创建sqlSession接⼝及实现类(主要封装crud⽅法)：

​	方法：selectList(String statementId,Object param)：查询所有

​	方法：selectOne(String statementId,Object param)：查询单个

​	方法：query(Configuration configuration, MapperStatement mapperStatement, Object... params)：具体实现，封装JDBC完成对数据库表的查询操作

​	方法：getMapper(Class<?> mapperClass)：通过JDK的动态代理生成代理对象，执行相应的方法（**代理模式**）

​	方法：getBounfSql(String sql)：完成对#{}的解析工作（**设计模式**）

