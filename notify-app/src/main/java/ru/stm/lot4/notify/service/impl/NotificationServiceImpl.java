package ru.stm.lot4.notify.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.stm.lot4.dto.model.PushNotificationRequest;
import ru.stm.lot4.notify.exception.ConvertException;
import ru.stm.lot4.notify.service.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<Long, PushNotificationRequest> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(PushNotificationRequest notification) {
        log.info("<=sending {}", writeValueAsString(notification));
        ListenableFuture<SendResult<Long, PushNotificationRequest>> future =
                kafkaTemplate.send("receiver_notification", notification);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    private String writeValueAsString(PushNotificationRequest dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error("Ошибка форматирования ",e);
            throw new ConvertException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
