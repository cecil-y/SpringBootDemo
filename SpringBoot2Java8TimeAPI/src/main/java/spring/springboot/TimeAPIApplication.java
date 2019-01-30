package spring.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SpringBootApplication
public class TimeAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeAPIApplication.class, args);
    }

    @RestController
    class HelloController {
        @PostMapping(value = "/user")
        public UserDto user(@RequestBody UserDto userDto) throws Exception {
            return userDto;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserDto {
        private String userName;
        private LocalDate birthday;
    }

//    LocalDate、LocalTime、LocalDateTime是Java 8开始提供的时间日期API，主要用来优化Java 8以前对于时间日期的处理操作。
//    然而，我们在使用Spring Boot或使用Spring Cloud Feign的时候，往往会发现使用请求参数或返回结果中有LocalDate、LocalTime、LocalDateTime的时候会发生各种问题。
//    本文我们就来说说这种情况下出现的问题，以及如何解决。

    @Bean
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
