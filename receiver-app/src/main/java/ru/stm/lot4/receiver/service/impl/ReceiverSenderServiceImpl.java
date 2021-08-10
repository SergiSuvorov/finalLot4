package ru.stm.lot4.receiver.service.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.db.repository.PushNotificationRepository;
import ru.stm.lot4.receiver.service.ReceiverSenderService;
import ru.stm.lot4.receiver.service.ReceiverServiceProducer;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiverSenderServiceImpl implements ReceiverSenderService {

    private final ReceiverServiceProducer producer;
    private final PushNotificationRepository notifyRepository;

    @Transactional
    @Scheduled(fixedRate = 5000, fixedDelay = 5000)
    public void sendNotification() {
        LocalDateTime dateTime = LocalDateTime.now();
        Iterable<PushNotification> notificationList = notifyRepository
                .findByDateBeforeAndActive(dateTime, true);
        for (PushNotification notification : notificationList) {
            producer.send(notification);
            notification.setActive(false);
            notifyRepository.save(notification);
        }

    }
}
