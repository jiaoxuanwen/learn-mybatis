

import com.itjiao.dao.IUserDao;
import com.itjiao.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {


    /**
     * mybatis入门案例
     * @param args
     */
    public static void main(String[] args) throws IOException {
//        读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

//        创建SqlSessionFactory工厂（构建者模式：把对象的创建细节隐藏，使用者调用方法即可拿到对象）
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

//        使用工厂生产SqlSession对象（工厂模式：解耦）
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        使用SqlSession创建Dao接口的代理对象（代理模式：不修改源码的基础上对已有方法增强）
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);

//        使用代理对象执行方法
        List<User> users= iUserDao.findAll();

        for (User user:users){
            System.out.println(user);
        }

//        释放资源

        sqlSession.close();
        inputStream.close();
    }
}
