package ru.stm.lot4.receiver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.receiver.service.ReceiverServiceProducer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiverServiceProducerImpl implements ReceiverServiceProducer {

    private final KafkaTemplate<Long, PushNotification> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(PushNotification pushNotification) {
        log.info("<=sending {}", writeValueAsString(pushNotification));
        ListenableFuture<SendResult<Long, PushNotification>> future =
                kafkaTemplate.send("messages", pushNotification);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    private String writeValueAsString(PushNotification dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
