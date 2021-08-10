package ru.stm.lot4.receiver.service;

import ru.stm.lot4.dto.model.PushNotificationRequest;

public interface ReceiverListenerService {

    void consume(PushNotificationRequest request);
}
