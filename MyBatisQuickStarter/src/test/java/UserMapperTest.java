import com.fude.mapper.UserMapper;
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
 * created at 2022/6/20 15:41
 * //TODO
 **/
public class UserMapperTest {
    @Test
    public void selectTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<MyBatisUser> list = mapper.findAll();
        list.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void findByUserTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        MyBatisUser user = new MyBatisUser();
//        user.setId(2);
        user.setUserName("检察长");
        List<MyBatisUser> list = mapper.findByUser(user);
        list.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void findByIdsTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<Integer> ids = Arrays.asList(1, 2);
        int[] ids = {1, 2};
        List<MyBatisUser> list = mapper.findByIds(ids);
        list.forEach(System.out::println);
        sqlSession.close();
    }
}
