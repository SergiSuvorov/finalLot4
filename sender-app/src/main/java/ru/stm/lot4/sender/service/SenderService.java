package ru.stm.lot4.sender.service;

import ru.stm.lot4.db.model.PushNotification;

public interface SenderService {

    void writeMessage(PushNotification request);
}
