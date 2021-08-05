package ru.stm.lot4.receiver.service;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.Phone;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.db.model.PushNotificationPhone;
import ru.stm.lot4.db.repository.PhoneRepository;
import ru.stm.lot4.db.repository.PushNotificationPhoneRepository;
import ru.stm.lot4.db.repository.PushNotificationRepository;
import ru.stm.lot4.dto.mappers.PushNotificationMapper;
import ru.stm.lot4.dto.model.PushNotificationRequest;
import scala.concurrent.impl.FutureConvertersImpl.P;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiverServiceImpl implements ReceiverService{
    private final PushNotificationRepository notifyRepo;
    private final PushNotificationMapper mapper;
    private final PhoneRepository phoneRepo;
    private final PushNotificationPhoneRepository pushNotificationPhoneRepo;
    @Override
    @KafkaListener(topics = "receiver_notification",containerFactory = "singleFactory")
    public void consume(PushNotificationRequest request) {
        log.info("=> consumed {}", request.toString());
        PushNotification pushNotification = mapper.fromRequest(request);
        PushNotification newNotification = notifyRepo.save(pushNotification);
        if(newNotification==null){
            log.info("fail create push notification " + pushNotification.toString());
        }
        Set<String> phones = request.getPhoneNumbers();
        for (String phoneNumber:phones
        ) {
            Phone phone = phoneRepo.findByNumber(phoneNumber)
                    .orElse(null);
            if(phone==null){
                log.info("not found phone with number " + phoneNumber);
                continue;
            }
            PushNotificationPhone pushNotificationPhone = new PushNotificationPhone();
            pushNotificationPhone.setPushNotification(pushNotification);
            pushNotificationPhone.setPhone(phone);
            pushNotificationPhoneRepo.save(pushNotificationPhone);
        }
    }
}
