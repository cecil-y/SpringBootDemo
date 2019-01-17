package spring.springboot;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.springboot.controller.HelloController;
import spring.springboot.controller.UserController;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController(), new UserController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    public void testUserController() throws Exception {
        // 测试UserController
        RequestBuilder requestBuilder = null;

        // 1.get查一下user列表,应该为空
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

        // 2.post提交一个user
        requestBuilder = post("/users/").param("id", "1").param("name", "Test").param("age", "20");
        mockMvc.perform(requestBuilder).andExpect(content().string(equalTo("success")));

        // 3.get获取user列表，应该有刚才输入的数据
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"Test\",\"age\":20}]")));

        // 4.put修改id为1的user
        requestBuilder = put("/users/1").param("name","TestPlus").param("age","21");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo("success")));

        // 5.get一个id为1的user
        requestBuilder = get("/users/1");
        mockMvc.perform(requestBuilder).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"TestPlus\",\"age\":21}")));

        // 6.del删除id为1的user
        requestBuilder = delete("/users/1");
        mockMvc.perform(requestBuilder).andExpect(content().string(equalTo("success")));

        // 7.get查一下user列表，应该为空
        requestBuilder = get("/users/");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

    }
}
