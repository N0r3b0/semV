package ksi.springbooks;

import  org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBooksApplication.class, args);
    }

}
