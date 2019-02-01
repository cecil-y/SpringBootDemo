package spring.springboot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MDBJDBCTApplicationTest {

    @Autowired
    @Qualifier(value = "primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier(value = "secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Before
    public void setUp() {
        jdbcTemplate1.update("DELETE FROM USER ");
        jdbcTemplate2.update("DELETE FROM USER ");
    }

    @Test
    public void test() throws Exception {

        //往第一个数据源中插入两条数据
        jdbcTemplate1.update("insert into user(id,name,age) values(?,?,?)", 1, "aaa", "10");
        jdbcTemplate1.update("insert into user(id,name,age) values(?,?,?)", 2, "bbb", "20");

        //往第二个数据源中插入一条数据，若插入的是第一个数据源，则主键会冲突
        jdbcTemplate2.update("insert into user(id,name,age) values(?,?,?)", 1, "ccc", "100");

        //查一下第一个数据源中的数据数量
        Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

        //查一下第二个数据源中的数据数量
        Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));
    }
}
