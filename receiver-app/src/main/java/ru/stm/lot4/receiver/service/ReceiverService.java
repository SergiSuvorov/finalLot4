package ru.stm.lot4.receiver.service;

import org.springframework.stereotype.Service;
import ru.stm.lot4.dto.model.PushNotificationRequest;

@Service
public interface ReceiverService {
    public void consume(PushNotificationRequest request);
}
