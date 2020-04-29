package com.itjiao.dao;


import com.itjiao.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {


    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();


    /**
     * 保存操作
     * @param user
     */
    void saveUser(User user);


    void updateUser(User user);

    void deleteUser(Integer userid);


//    查询1个
    User findUserById(Integer id);


//    模糊查询
    List<User> findByName(String name);


//    使用聚合函数
//    查询总用户数
    Integer getAllUserCount();


}
