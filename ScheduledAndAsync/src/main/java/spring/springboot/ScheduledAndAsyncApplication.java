package spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling   //启动定时器功能
@EnableAsync    //启动异步功能
public class ScheduledAndAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledAndAsyncApplication.class, args);
    }

}
