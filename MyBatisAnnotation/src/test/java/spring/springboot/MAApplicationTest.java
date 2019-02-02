package spring.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.entity.User;
import spring.springboot.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MAApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void test() throws Exception {
        //insert -- property
        userMapper.insert("AAA", 20);
        //select
        User user = userMapper.findByName("AAA");
        Assert.assertEquals(20, user.getAge().intValue());

        //update -- object
        user.setAge(30);
        userMapper.update(user);
        user = userMapper.findByName("AAA");
        Assert.assertEquals(30, user.getAge().intValue());

        //delete -- object
        userMapper.delete(user);
        user = userMapper.findByName("AAA");
        Assert.assertNull(user);

        //insert -- object
        user = new User("BBB", 30);
        userMapper.insertByUser(user);
        Assert.assertEquals(30, userMapper.findByName("BBB").getAge().intValue());

        //insert -- map
        Map<String, Object> map = new HashMap<>();
        map.put("name", "CCC");
        map.put("age", 40);
        userMapper.insertByMap(map);
        Assert.assertEquals(40, userMapper.findByName("CCC").getAge().intValue());

        //select -- List<User>
        List<User> userList = userMapper.findAll();
        for (User u : userList) {
            Assert.assertNull(user.getId());
            Assert.assertNotNull(user.getName());
        }
    }
}
