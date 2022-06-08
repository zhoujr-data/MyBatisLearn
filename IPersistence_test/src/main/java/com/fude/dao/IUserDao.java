package com.fude.dao;

import com.fude.pojo.User;

import java.util.List;

/**
 * @author zhoujr
 * created at 2022/6/7 11:05
 * //TODO
 **/
public interface IUserDao {

    // 查询所有
    List<User> findAll() throws Exception;

    // 根据条件查询
    User findByUser(User user) throws Exception;

}
