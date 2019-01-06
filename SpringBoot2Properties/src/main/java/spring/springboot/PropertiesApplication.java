package spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import spring.springboot.entity.FooProperties;
import spring.springboot.entity.PostInfo;

import java.util.List;

@SpringBootApplication
public class PropertiesApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(PropertiesApplication.class, args);

        Binder binder = Binder.get(applicationContext.getEnvironment());

        //绑定简单配置
        FooProperties fooProperties = binder.bind("com.didispace", Bindable.of(FooProperties.class)).get();
        System.out.println(fooProperties);

        // 绑定List配置
        List<String> post = binder.bind("com.didispace.post",Bindable.listOf(String.class)).get();
        System.out.println(post);

        List<PostInfo> postInfos = binder.bind("com.didispace.posts",Bindable.listOf(PostInfo.class)).get();
        System.out.println(postInfos);

        // 读取配置
        System.out.println(applicationContext.getEnvironment().containsProperty("com.didispace.database-platform"));
        System.out.println(applicationContext.getEnvironment().containsProperty("com.didispace.database-Platform"));
        System.out.println(applicationContext.getEnvironment().containsProperty("com.didispace.databasePlatform"));
        System.out.println(applicationContext.getEnvironment().containsProperty("com.didispace.databaseplatform"));
        System.out.println(applicationContext.getEnvironment().containsProperty("com.didispace.database.platform"));
    }
}
