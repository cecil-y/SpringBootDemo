package spring.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }

    /* 说明 -- 顺序
    ApplicationStartingEvent
    ApplicationEnvironmentPreparedEvent
    ApplicationPreparedEvent
    ApplicationStartedEvent <= 新增的事件
    ApplicationReadyEvent
    ApplicationFailedEvent*/

    @Bean
    public DataLoader dataLoader(){
        return new DataLoader();
    }

    @Slf4j
    static class DataLoader implements CommandLineRunner {
        @Override
        public void run(String... strings) throws Exception {
            log.info("Loading data...");
        }
    }
}