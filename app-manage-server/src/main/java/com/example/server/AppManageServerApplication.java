package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example")
@EntityScan(basePackages = "com.example")

public class AppManageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppManageServerApplication.class, args);
    }

}
