package ru.stm.lot4.receiver.service;

import ru.stm.lot4.db.model.PushNotification;

public interface ReceiverServiceProducer {

    void send(PushNotification pushNotification);
}
