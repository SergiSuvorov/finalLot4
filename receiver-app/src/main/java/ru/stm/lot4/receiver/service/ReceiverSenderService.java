package ru.stm.lot4.receiver.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.db.repository.PushNotificationRepository;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class ReceiverSenderService {

    private final ReceiverServiceProducer producer;
    private final PushNotificationRepository notifyRepo;

    @Scheduled(fixedRate = 5000)
    public void sendNotification(){
        LocalDateTime dateTime = LocalDateTime.now();
        Iterable<PushNotification> notificationList = notifyRepo.findByDateBeforeAndActive(dateTime,true);
        for (PushNotification notification :notificationList) {
            producer.send(notification);
            notification.setActive(false);
            notifyRepo.save(notification);
        }

    }
}
