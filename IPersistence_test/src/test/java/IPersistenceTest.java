import com.fude.dao.IUserDao;
import com.fude.io.Resources;
import com.fude.pojo.User;
import com.fude.sqlSession.SqlSession;
import com.fude.sqlSession.SqlSessionFactory;
import com.fude.sqlSession.impl.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author zhoujr
 * created at 2022/5/23 17:41
 *
 **/
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用
        User user = new User();
        user.setId(1);
        user.setName("张三");
//        User user1 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user1.toString());
//        List<User> users = sqlSession.selectList("user.selectList");
//        users.forEach(System.out::println);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> arr = userDao.findAll();
        arr.forEach(System.out::println);
        User result = userDao.findByUser(user);
        System.out.println(result);
    }

}
