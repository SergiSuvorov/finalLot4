package ru.stm.lot4.db.repository;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stm.lot4.db.model.PushNotification;
@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotification,Long> {
    Iterable<PushNotification> findByDateBeforeAndActive(LocalDateTime dateTime,boolean active);
}
