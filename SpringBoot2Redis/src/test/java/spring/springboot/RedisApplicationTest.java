package spring.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisApplicationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String , User> redisTemplate;

    /**
     * 需要把跟mysql之类相关的jar包注释，否则会报没有配置url等信息
     * @throws Exception
     */
    @Test
    public void test() throws Exception{

        //保存字符串
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));

        //保存对象
        User user = new User("超人",20);
        redisTemplate.opsForValue().set(user.getUsername(),user);
        user = new User("蝙蝠侠",30);
        redisTemplate.opsForValue().set(user.getUsername(),user);
        user = new User("蜘蛛侠",40);
        redisTemplate.opsForValue().set(user.getUsername(),user);

        //提取的时候map类型的值未能转为实体，会报错
        Assert.assertEquals(20,redisTemplate.opsForValue().get("超人").getAge().intValue());
        Assert.assertEquals(30,redisTemplate.opsForValue().get("蝙蝠侠").getAge().intValue());
        Assert.assertEquals(40,redisTemplate.opsForValue().get("蜘蛛侠").getAge().intValue());
    }
}
