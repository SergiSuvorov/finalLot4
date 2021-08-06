package ru.stm.lot4.receiver.service;

import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.model.PushNotificationRequest;

public interface ReceiverServiceProducer {
    void send(PushNotification pushNotification);
}
