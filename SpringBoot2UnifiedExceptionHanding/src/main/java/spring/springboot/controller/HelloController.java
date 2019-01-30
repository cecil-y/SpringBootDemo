package spring.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.springboot.exception.MyException;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping(value = "/json")
    public String json() throws Exception {
        throw new MyException("发生错误2");
    }

    @RequestMapping(value = "/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://www.baidu.com");
        return "index";
    }

}
