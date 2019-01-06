package spring.springboot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.springboot.entity.UserProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertiesApplication.class)
public class PropertiesTest {

    private static final Log log = LogFactory.getLog(PropertiesTest.class);

    @Autowired
    private UserProperties userProperties;

    /**
     * 配置的属性检查，是否一致
     * @throws Exception
     */
    @Test
    public void getHello() throws Exception{
        Assert.assertEquals(userProperties.getName(),"Jack");
        Assert.assertEquals(userProperties.getAge(),new Integer(18));
        log.info("属性间的引用"+userProperties.getDesc());

        log.info("随机数测试输出：");
        log.info("随机字符串 : " + userProperties.getValue());
        log.info("随机int : " + userProperties.getNumber());
        log.info("随机long : " + userProperties.getL());
        log.info("随机10以下 : " + userProperties.getNum1());
        log.info("随机10-20 : " + userProperties.getNum2());

    }
}
