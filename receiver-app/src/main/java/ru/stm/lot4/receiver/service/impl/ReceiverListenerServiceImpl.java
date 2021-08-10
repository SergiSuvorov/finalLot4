package ru.stm.lot4.receiver.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.db.repository.PhoneRepository;
import ru.stm.lot4.db.repository.PushNotificationRepository;
import ru.stm.lot4.dto.mappers.PushNotificationMapper;
import ru.stm.lot4.dto.model.PushNotificationRequest;
import ru.stm.lot4.receiver.service.ReceiverListenerService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiverListenerServiceImpl implements ReceiverListenerService {

    private final PushNotificationRepository notifyRepo;
    private final PushNotificationMapper mapper;
    private final PhoneRepository phoneRepo;

    @Override
    @KafkaListener(topics = "receiver_notification", containerFactory = "singleFactory")
    public void consume(PushNotificationRequest request) {
        log.info("=> consumed {}", request.toString());
        PushNotification pushNotification = mapper.fromRequest(request);
        List<Phone> phones = request.getPhoneNumbers()
                .stream()
                .distinct()
                .map(phoneRepo::findByNumber)
                .filter(phone -> phone.isPresent())
                .map(phone -> phone.get())
                .collect(Collectors.toList());

        pushNotification.setPhones(phones);
        PushNotification newNotification = notifyRepo.save(pushNotification);
        log.info("created new notification " + newNotification);
    }
}
