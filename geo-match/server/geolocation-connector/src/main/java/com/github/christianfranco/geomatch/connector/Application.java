package com.github.christianfranco.geomatch.connector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Christian Franco on 18/12/2016.
 */
@SpringBootApplication
@ImportResource("classpath:application-context.xml")
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
