package spring.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.entity.User;
import spring.springboot.repository.UserRepository;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@Transactional() //事务
public class SDJApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception{
        //创建10条信息
        userRepository.save(new User("AAA",10));
        userRepository.save(new User("BBB",20));
        userRepository.save(new User("CCC",30));
        userRepository.save(new User("DDD",40));
        userRepository.save(new User("EEE",50));
        userRepository.save(new User("FFF",60));
        userRepository.save(new User("GGG",70));
        userRepository.save(new User("HHH",80));
        userRepository.save(new User("III",90));
        userRepository.save(new User("JJJ",100));

        //测试findAll,查询所有记录
        Assert.assertEquals(10,userRepository.findAll().size());

        //测试findByName,查询姓名为FFF的
        Assert.assertEquals(60,userRepository.findByName("FFF").getAge().intValue());

        //测试findUser
        Assert.assertEquals(80,userRepository.findUser("HHH").getAge().intValue());

        //测试findByNameAndAge
        Assert.assertEquals(30,userRepository.findByNameAndAge("CCC",30).getAge().intValue());

        //测试删除姓名为JJJ的user
        userRepository.delete(userRepository.findByName("JJJ"));

        Assert.assertEquals(9,userRepository.findAll().size());
    }
}
