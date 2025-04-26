package ua.opnu.practice1_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ua.opnu.practice1_template.controller"})
@EntityScan("ua.opnu.practice1_template.model")
@EnableJpaRepositories("ua.opnu.practice1_template.repository")
public class Practice1TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(Practice1TemplateApplication.class, args);
    }
}


