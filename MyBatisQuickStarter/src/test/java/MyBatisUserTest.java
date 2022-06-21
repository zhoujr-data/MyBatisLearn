import com.fude.pojo.MyBatisUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhoujr
 * created at 2022/6/14 16:27
 * Mybatis
 **/
public class MyBatisUserTest {

    @Test
    public void deleteTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count = sqlSession.delete("com.fude.mapper.UserMapper.deleteUser", 5);
        sqlSession.commit();
        System.out.println(count);
        sqlSession.close();
    }

    @Test
    public void updateTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyBatisUser user = new MyBatisUser();
        user.setId(4);
        user.setUserName("lucy");
        int count = sqlSession.update("com.fude.mapper.UserMapper.updateUser", user);
        sqlSession.commit();
        System.out.println(count);
        sqlSession.close();
    }

    @Test
    public void insertTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyBatisUser user = new MyBatisUser();
        user.setId(5);
        user.setUserName("tom");
        int count = sqlSession.insert("com.fude.mapper.UserMapper.inseterUser", user);
        sqlSession.commit();
        System.out.println(count);
        sqlSession.close();
    }

    @Test
    public void sqlSessionTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<MyBatisUser> list = sqlSession.selectList("com.fude.mapper.UserMapper.findAll");
        list.forEach(System.out::println);
        sqlSession.close();
    }

}
