package ru.stm.lot4.notify.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stm.lot4.db.model.PushNotification;
import ru.stm.lot4.dto.mappers.PushNotificationMapper;
import ru.stm.lot4.dto.model.PushNotificationRequest;
import ru.stm.lot4.notify.service.NotificationService;

@RestController
@RequestMapping("/receiver/pushNotification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationService kafkaService;
    private PushNotificationMapper notificationMapper;


    @PostMapping("/create")
    public ResponseEntity<PushNotification> createNotification(@Valid @RequestBody PushNotificationRequest notificationRequest){

        kafkaService.send(notificationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}