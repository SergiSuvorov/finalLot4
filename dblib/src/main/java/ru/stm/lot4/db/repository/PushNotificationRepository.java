package ru.stm.lot4.db.repository;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.stm.lot4.db.model.PushNotification;

public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

    Iterable<PushNotification> findByDateBeforeAndActive(LocalDateTime dateTime, boolean active);
}
