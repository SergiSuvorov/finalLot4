package ru.stm.lot4.dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.stm.lot4")
public class DtoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DtoApplication.class);
    }
}
