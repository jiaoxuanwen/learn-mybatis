import com.itjiao.dao.IUserDao;
import com.itjiao.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream inputStream;
    private SqlSession sqlSession;
    private IUserDao iUserDao;


    //    加了注解在测试方法执行之前执行
    @Before
    public void init() throws IOException {
        //        读取配置文件
        this.inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

//        创建SqlSessionFactory工厂（构建者模式：把对象的创建细节隐藏，使用者调用方法即可拿到对象）
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

//        使用工厂生产SqlSession对象（工厂模式：解耦）
        this.sqlSession = sqlSessionFactory.openSession();

//        使用SqlSession创建Dao接口的代理对象（代理模式：不修改源码的基础上对已有方法增强）
        this.iUserDao = sqlSession.getMapper(IUserDao.class);
    }


    //    加了注解在测试方法执行之后执行
    @After
    public void destroy() throws IOException {
        //        提交事务
        this.sqlSession.commit();
        this.sqlSession.close();
        this.inputStream.close();
    }


    @Test
    public void testFindAll() {
        List<User> users = this.iUserDao.findAll();

        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("fftu");
        user.setAddress("张家界222");
        user.setBirthday(new Date());
        user.setSex("男");

        this.iUserDao.saveUser(user);

////        提交事务
//        this.sqlSession.commit();
    }


    @Test
    public void testUpdate() {
        User user = new User();
        user.setUsername("刘亦菲");
        user.setAddress("new york");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setId(51);
        this.iUserDao.updateUser(user);
    }

    @Test
    public void testDelete(){
        this.iUserDao.deleteUser(50);
    }

    @Test
    public void testFindUserById(){
        User user = this.iUserDao.findUserById(48);
        System.out.println(user.toString());
    }

    @Test
    public void testFindByName(){
        List<User> userList = this.iUserDao.findByName("%二%");
//        List<User> userList = this.iUserDao.findByName("二");

        for(User user : userList){
            System.out.println(user.toString());
        }
    }

    @Test
    public void testGetAllUserCount(){
        Integer count = this.iUserDao.getAllUserCount();
        System.out.println(count);
    }
}
