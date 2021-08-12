package ru.stm.lot4.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = "ru.stm.lot4")
//@EntityScan("ru.stm.lot4.db.model")
//@EnableJpaRepositories("ru.stm.lot4.db.repository")
public class DeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class);
    }
}
