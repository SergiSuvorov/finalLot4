package ru.stm.lot4.db.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.stm.lot4.db.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findByNumber(String phoneNumber);
}
