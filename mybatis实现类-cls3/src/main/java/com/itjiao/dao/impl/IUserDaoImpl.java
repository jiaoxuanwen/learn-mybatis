package com.itjiao.dao.impl;

import com.itjiao.dao.IUserDao;
import com.itjiao.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IUserDaoImpl implements IUserDao {
    private SqlSessionFactory sqlSessionFactory;

    //    这个方法相当于重写了这个impl的构造方法
    public IUserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<User> findAll(){

//        使用工厂创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        使用session执行查询所有方法
        List<User> users = sqlSession.selectList("com.itjiao.dao.IUserDao.findAll");
        sqlSession.close();

//        返回查询结果
        return users;





    }
}
