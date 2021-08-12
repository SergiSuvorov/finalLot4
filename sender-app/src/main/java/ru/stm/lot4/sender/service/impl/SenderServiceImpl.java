package ru.stm.lot4.sender.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.mappers.PushNotificationMapper;
import ru.stm.lot4.sender.service.SenderService;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderServiceImpl implements SenderService {

    private final KafkaTemplate<Long, PushNotification> senderKafka;
    private final PushNotificationMapper mapper;

    @Value("${messageDir}")
    private String messageDir;

    @Value("${countThread}")
    private int countThread;

    @KafkaListener(topics = "messages", containerFactory = "singleFactory")
    public void writeMessage(PushNotification pushNotification) {
        CompletableFuture.runAsync(() -> writeLog(pushNotification),
                Executors.newFixedThreadPool(countThread));
    }

    public void writeLog(PushNotification notification) {
        try (FileWriter logMessage = new FileWriter(messageDir + notification.getTitle() +
                "_" + UUID.randomUUID())) {
            logMessage.write("push_notification" + '\n');
            logMessage.write(notification.toString() + '\n');
        } catch (IOException e) {
            log.error("ошибка записи в файл: " + e);
        }
    }
}
