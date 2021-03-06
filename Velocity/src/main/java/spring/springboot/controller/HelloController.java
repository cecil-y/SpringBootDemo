package spring.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value = "/")
    public String index(ModelMap map){
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host","http://www.baidu.com");
        // return模板文件的名称，对应src/main/resources/templates/index.vm
        return "index";
    }
}
