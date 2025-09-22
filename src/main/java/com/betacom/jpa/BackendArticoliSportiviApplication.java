package com.betacom.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.betacom.jpa.models")
public class BackendArticoliSportiviApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendArticoliSportiviApplication.class, args);
    }
}
