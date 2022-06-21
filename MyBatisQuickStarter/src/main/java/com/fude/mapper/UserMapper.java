package com.fude.mapper;

import com.fude.pojo.MyBatisUser;

import java.util.List;

/**
 * @author zhoujr
 * created at 2022/6/14 16:22
 * //TODO
 **/
public interface UserMapper {
    List<MyBatisUser> findAll();

    int inseterUser(MyBatisUser record);

    int updateUser(MyBatisUser record);

    int deleteUser(Integer id);

    List<MyBatisUser> findByUser(MyBatisUser user);

    List<MyBatisUser> findByIds(int[] ids);
}
