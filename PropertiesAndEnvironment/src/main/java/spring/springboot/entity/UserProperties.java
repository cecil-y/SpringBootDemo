package spring.springboot.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;

//@Component    //Component注入的Bean不一样
@Configuration  //Configuration注入的Bean一样
@PropertySource(value = "classpath:application-value.properties")   //配置文件的路径
public class UserProperties {

    @Value("${com.demo.springboot.name}")
    private String name;

    @Value("${com.demo.springboot.age}")
    private Integer age;

    @Value("${com.demo.springboot.desc}")
    private String desc;

    @Value("${com.demo.random.value}")
    private String value;

    @Value("${com.demo.random.number}")
    private Integer number;

    @Value("${com.demo.random.long}")
    private Long l;

    @Value("${com.demo.random.underTen}")
    private Integer num1;

    @Value("${com.demo.random.underTwenty}")
    private Integer num2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
