package ru.stm.lot4.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stm.lot4.db.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
