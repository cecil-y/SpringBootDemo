package spring.springboot.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource(value="application.properties")
public class FooProperties {

    private String foo;

    private String databasePlatform;
}
