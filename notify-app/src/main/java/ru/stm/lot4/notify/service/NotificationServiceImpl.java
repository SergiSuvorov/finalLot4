package ru.stm.lot4.notify.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.stm.lot4.dto.model.PushNotificationRequest;
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private KafkaTemplate<Long,PushNotificationRequest> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper ;
    @Override
    public void send(PushNotificationRequest notification) {
        log.info("<=sending {}",writeValueAsString(notification));
        ListenableFuture<SendResult<Long, PushNotificationRequest>> future =
                kafkaTemplate.send("receiver_notification",notification);
        future.addCallback(System.out::println,System.err::println);
        kafkaTemplate.flush();
    }

    private String writeValueAsString(PushNotificationRequest dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
