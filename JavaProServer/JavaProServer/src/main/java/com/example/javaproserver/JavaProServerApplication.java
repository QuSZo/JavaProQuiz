package com.example.javaproserver;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class JavaProServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProServerApplication.class, args);
    }
}
