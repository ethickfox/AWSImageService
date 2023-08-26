package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.epam")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}