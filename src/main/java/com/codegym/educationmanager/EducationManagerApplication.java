package com.codegym.educationmanager;

import com.codegym.educationmanager.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EducationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationManagerApplication.class, args);
    }

}
