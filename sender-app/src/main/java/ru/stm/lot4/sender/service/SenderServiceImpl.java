package ru.stm.lot4.sender.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.mappers.PushNotificationMapper;
import ru.stm.lot4.dto.model.PushNotificationRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderServiceImpl implements SenderService{

    private final KafkaTemplate<Long, PushNotificationRequest> senderKafka;
    private final PushNotificationMapper mapper;
    @Value("${MessageDir}")
    private String MessageDir;
    @KafkaListener(topics = "messages",groupId = "messageGroup ")
    public void writeMessage(PushNotification pushNotification) {
        try(FileWriter logMessage = new FileWriter(MessageDir + pushNotification.getTitle() +
                "_" + UUID.randomUUID())){
            logMessage.write("push_notification" + '\n');
            logMessage.write(pushNotification.toString() + '\n');
        }
        catch (IOException e){
            log.error(e.toString());
        }
    }
}
