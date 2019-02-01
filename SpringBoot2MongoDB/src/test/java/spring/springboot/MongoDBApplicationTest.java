package spring.springboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.entity.User;
import spring.springboot.entity.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoDBApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void test() throws Exception{

        // 创建三个User并验证
        userRepository.save(new User(1L,"didi",30));
        userRepository.save(new User(2L,"mama",40));
        userRepository.save(new User(3L,"kaka",50));
        Assert.assertEquals(3,userRepository.findAll().size());

        //删除一个User并验证
        User u = userRepository.findById(1L).get();
        userRepository.delete(u);
        Assert.assertEquals(2,userRepository.findAll().size());

        //删除一个User并验证
        u = userRepository.findByUsername("mama");
        userRepository.delete(u);
        Assert.assertEquals(1,userRepository.findAll().size());

    }
}
