package ckmbks.demo.apiserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
}, scanBasePackages = "ckmbks.demo")
@ComponentScan(basePackages = "ckmbks")
@EntityScan(basePackages = {"ckmbks.demo"})
@EnableJpaRepositories(basePackages = {"ckmbks.demo"})
@MapperScan("ckmbks.demo.dataaccess")
public class DemoApiserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApiserverApplication.class, args);
    }

}

