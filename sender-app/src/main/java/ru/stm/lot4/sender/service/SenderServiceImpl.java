package ru.stm.lot4.sender.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.mappers.PushNotificationMapper;
import ru.stm.lot4.dto.model.PushNotificationRequest;

@Service
@RequiredArgsConstructor
public class SenderServiceImpl {
    private final KafkaTemplate<Long, PushNotificationRequest> senderKafka;
    private final PushNotificationMapper mapper;
    @Value("${MessageDir}")
    private String MessageDir;
    @KafkaListener(topics = "messages",groupId = "messageGroup ")
    public void writeMessage(PushNotificationRequest request) {
        try(FileWriter logMessage = new FileWriter(MessageDir + request.getTitle() + "_" + UUID
                .randomUUID())){
            logMessage.write("push_notification" + '\n');
            PushNotification notification = mapper.fromRequest(request);
            logMessage.write(notification.toString() + '\n');
            logMessage.write("Phone list" + '\n');
            logMessage.write(request.getPhoneNumbers().toString());
            logMessage.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
