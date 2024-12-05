package com.moreira.desafiomglu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioMgluApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioMgluApplication.class, args);
    }

}
