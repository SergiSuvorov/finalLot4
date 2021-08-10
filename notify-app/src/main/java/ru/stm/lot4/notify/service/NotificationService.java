package ru.stm.lot4.notify.service;

import ru.stm.lot4.dto.model.PushNotificationRequest;

public interface NotificationService {

    void send(PushNotificationRequest notificationRequest);
}
