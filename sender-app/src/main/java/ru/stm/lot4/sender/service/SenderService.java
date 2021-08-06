package ru.stm.lot4.sender.service;

import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.PushNotification;

@Service
public interface SenderService {
    void writeMessage(PushNotification request);
}
