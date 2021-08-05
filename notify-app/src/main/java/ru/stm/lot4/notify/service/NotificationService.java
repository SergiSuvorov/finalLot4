package ru.stm.lot4.notify.service;

import org.springframework.stereotype.Service;
import ru.stm.lot4.dto.model.PushNotificationRequest;

@Service
public interface NotificationService {

    void send(PushNotificationRequest notificationRequest);
}
