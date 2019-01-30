package spring.springboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JDBCApplicationTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        //准备，清空user表
        userService.deleteAllUser();
    }

    @Test
    public void test() throws Exception{
        //插入5个用户
        userService.create("a",1);
        userService.create("b",2);
        userService.create("c",3);
        userService.create("d",4);
        userService.create("e",5);

        //查库
        Assert.assertEquals(5,userService.getAllUsers().intValue());

        //删除2个用户
        userService.deleteByName("d");
        userService.deleteByName("b");

        //查库
        Assert.assertEquals(3,userService.getAllUsers().intValue());
    }
}
