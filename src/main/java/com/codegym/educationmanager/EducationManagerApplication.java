package com.codegym.educationmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EducationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationManagerApplication.class, args);
    }

}
