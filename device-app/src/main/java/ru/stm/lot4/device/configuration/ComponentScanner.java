package ru.stm.lot4.device.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ru.stm.lot4")
@EntityScan("ru.stm.lot4.db.model")
@EnableJpaRepositories("ru.stm.lot4.db.repository")
public class ComponentScanner {

}
