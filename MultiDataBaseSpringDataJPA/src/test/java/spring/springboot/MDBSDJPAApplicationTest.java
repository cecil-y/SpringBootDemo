package spring.springboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.entity.primary.User;
import spring.springboot.entity.primary.UserRepository;
import spring.springboot.entity.secondary.Message;
import spring.springboot.entity.secondary.MessageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MDBSDJPAApplicationTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void setUp(){

    }

    @Test
    public void test() throws Exception{
        userRepository.save(new User("aaa",10));
        userRepository.save(new User("bbb",20));
        userRepository.save(new User("ccc",30));
        userRepository.save(new User("ddd",40));
        userRepository.save(new User("eee",50));

        Assert.assertEquals(5,userRepository.findAll().size());

        messageRepository.save(new Message("01","aaaaa"));
        messageRepository.save(new Message("02","bbbbb"));
        messageRepository.save(new Message("03","ccccc"));

        Assert.assertEquals(3,messageRepository.findAll().size());
    }
}
